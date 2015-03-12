package com.generic.core.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Size;
import com.generic.core.onboarding.exceldto.ExcelSheetObject;
import com.generic.core.onboarding.exceldto.ExcelSizeDto;
import com.generic.core.respository.SizeRepository;
import com.generic.core.services.service.SizeServiceI;
import com.generic.core.utilities.Util;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.ResponseDto;

@Service
@Transactional
public class SizeService implements SizeServiceI{

	@Resource
	SizeRepository sizeRepository;


	@Override
	public List<Size> findAllQuantity() {
		
		return sizeRepository.findAll();
	}


	//sizeId == quantiryId
	@Override
	public List<ResponseDto> onboardSize(ExcelSheetObject excelSheetObject) {

		List<String> sizeInsertedIds = new ArrayList<String>();
		List<ResponseDto> response = new ArrayList<ResponseDto>();
		int count = 1;
		
		for(Object anSizeObject : excelSheetObject.getRows()) {
			ExcelSizeDto aSheetRow = (ExcelSizeDto)anSizeObject;
			Size aSize = new Size(aSheetRow.getQuantityId(), aSheetRow.getQuantityName(), aSheetRow.getUnit(), aSheetRow.getPermissibleValues());
			try {
				sizeRepository.save(aSize);
				sizeInsertedIds.add(aSize.getSizeId());
			} catch (Exception e) {
				String errorResponse = Util.generateErrorString(count, Constants.LOGGER_WARNING, e.getMessage());
				response.add(new ResponseDto(Constants.DATABASE_ERROR, errorResponse));
			}
		}

		if(!response.isEmpty()) {
			for(String aSizeInsertedId : sizeInsertedIds) {
				sizeRepository.delete(new Size(aSizeInsertedId));
			}
		} else {			// no error send success message
			String successResponse = Constants.SUCCESS_RESPONSE_MESSAGE + ". Records Insserted :" + count;
			response.add(new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, successResponse));
		}
		return response;
	}
}


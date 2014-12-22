package com.generic.core.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.core.model.entities.ShopIdItemId;
import com.generic.core.model.entities.Shops;
import com.generic.core.model.entities.ShopsItems;

@Repository
public interface ShopsItemsRepository extends JpaRepository<ShopsItems, ShopIdItemId>{

	List<ShopsItems> findByShopIdItemIdShop(Shops shops);
}

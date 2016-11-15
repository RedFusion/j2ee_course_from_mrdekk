package ru.menkin.ea.lec3.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

public class CapacityWarehouseFactory implements ObjectFactory {
	@Override
	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
			throws Exception {
		if (obj instanceof Reference) {
			Reference ref = (Reference) obj;

			if (ref.getClassName().equals(CapacityWarehouse.class.getName())) {
				RefAddr addrRef = ref.get(CapacityWarehouse.REF_ADDR_REF);
				RefAddr addrCapacityStorage = ref.get(CapacityWarehouse.REF_ADDR_CAPACITY_WAREHOUSE);
				RefAddr addrCapacityCategory = ref.get(CapacityWarehouse.REF_ADDR_CAPACITY_CATEGORY);
				RefAddr addrUpdateTime = ref.get(CapacityWarehouse.REF_ADDR_UPDATE_TIME);

				boolean isNotNull = (null != addrRef && null != addrCapacityStorage && 
									 null != addrCapacityCategory && null != addrUpdateTime);
				if (isNotNull) {
					return new 
							CapacityWarehouse(addrRef.getContent().toString(),
							Double.parseDouble(addrCapacityStorage.getContent().toString()),
							Double.parseDouble(addrCapacityCategory.getContent().toString()),
							Integer.parseInt(addrUpdateTime.getContent().toString()));
				}
			}
		}
		return null;
	}
}
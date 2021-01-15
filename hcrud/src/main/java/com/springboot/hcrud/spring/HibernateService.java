package com.springboot.hcrud.spring;

import java.util.List;

public interface HibernateService {
    <T> void hibernateInsertEntities(List<T> listItems, Class<T> entity);
    <T> void hibernateInsertEntity(T item, Class<T> entity);
    <T> List<T> hibernateRetrieveAllData(Class<T> entityClass);
}

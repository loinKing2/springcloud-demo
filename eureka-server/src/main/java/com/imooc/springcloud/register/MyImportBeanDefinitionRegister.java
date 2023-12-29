package com.imooc.springcloud.register;

import com.imooc.springcloud.proxy.MyFactoryBean;
import com.imooc.springcloud.service.IService;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;


public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition();
        beanDefinitionBuilder.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_NAME);
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionBuilder.getBeanDefinition();
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(IService.class.getName());
        beanDefinition.setBeanClass(MyFactoryBean.class);
        registry.registerBeanDefinition("iService",beanDefinition);
    }



}

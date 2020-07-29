package com.lovefn.grace.mybatis.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * 分页插件
 * 关于该功能插件，mybatis已有RowBoundsPlugin，但功能上有缺陷：
 * 1. RowBounds的构造方法new RowBounds(offset, limit)中的offset、limit参数就相当于MySQL的select语句limit后的offset和rows，但实际上最终的select语句并没有使用limit
 * 2. 实际上RowBounds原理是通过ResultSet的游标来实现分页，也就是并不是用select语句的limit分页而是用Java代码分页，查询语句的结果集会包含符合查询条件的所有数据，使用不慎会导致性能问题
 * 因此，重新实现分页插件如下
 */
public class PaginationPlugin extends AbstractMBGPlugin {
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // add field, getter, setter for limit clause
        addField(topLevelClass, introspectedTable, "limitStart", null);
        addField(topLevelClass, introspectedTable, "limitEnd", null);
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        // XmlElement isParameterPresenteElemen = (XmlElement) element
        // .getElements().get(element.getElements().size() - 1);
        XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
        isNotNullElement.addAttribute(new Attribute("test", "limitStart != null and limitStart>=0")); //$NON-NLS-1$ //$NON-NLS-2$
        //      isNotNullElement.addAttribute(new Attribute("compareValue", "0")); //$NON-NLS-1$ //$NON-NLS-2$
        isNotNullElement.addElement(new TextElement("limit #{limitStart} , #{limitEnd}"));
        // isParameterPresenteElemen.addElement(isNotNullElement);
        element.addElement(isNotNullElement);
        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }


    public boolean validate(List<String> warnings) {
        return true;
    }

}
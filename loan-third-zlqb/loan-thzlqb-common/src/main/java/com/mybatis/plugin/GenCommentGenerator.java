package com.mybatis.plugin;

import java.util.Properties;
import java.util.regex.Matcher;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import com.shangyong.common.utils.StringUtil;

public class GenCommentGenerator implements CommentGenerator {

	/**
	 * 给字段添加数据库备注
	 * 
	 * @param field
	 * @param introspectedTable
	 * @param introspectedColumn
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {

		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			String value = introspectedColumn.getRemarks();

			Matcher matcher = StringUtil.matcherCaseInsensitive("\\[(.+?)\\]", value);
			value = StringUtil.replaceAllMatch(value, "\\[(.+?)\\]", "");

			field.addJavaDocLine("//" + value);

			// 特殊标识

			if (matcher.find()) {
				field.addAnnotation("@ApiModelProperty(value = \"" + value +"[valid:add]"+ "\")");
			} else {
				field.addAnnotation("@ApiModelProperty(value = \"" + value + "\")");
				return;
			}
			String selfRegex = matcher.group(1);
			// [valid:add]
			String[] selfRegexs = selfRegex.split(":");

			StringBuilder groupValue = new StringBuilder("groups={");
			for (String fun : selfRegexs[1].split("-")) {
				groupValue.append(StringUtil.getCamelCaseString(selfRegexs[0] + "-" + fun, true)).append(".class,");
			}
			groupValue.deleteCharAt(groupValue.length() - 1).append("}");

			String replaceValue = StringUtil.replaceAllMatch(value, "\\((.+?)\\)", "");
			replaceValue = StringUtil.replaceAllMatch(replaceValue, "（(.+?)）", "");

			// 判断是不是需要校验
			if (!introspectedColumn.isNullable()) {
				field.addAnnotation("@NotNull(message=\"" + replaceValue + "不能为空\"," + groupValue + ")");
			}

			// size校验
			if (introspectedColumn.isStringColumn() && introspectedColumn.getLength() > 0) {
				field.addAnnotation("@Size(max=" + introspectedColumn.getLength() + ",message=\"" + replaceValue
						+ "长度不能超过{max}\"," + groupValue + ")");
			}

		}
	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		boolean isHasCheck = introspectedTable.getAllColumns().stream().anyMatch((introspectedColumn) -> {
			return StringUtil.matcherCaseInsensitive("\\[(.+?)\\]", introspectedColumn.getRemarks()).find();
		});
		topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
		if (isHasCheck) {
			topLevelClass.addImportedType("javax.validation.constraints.NotNull");
			topLevelClass.addImportedType("javax.validation.constraints.Size");
		}
	}

	/**
	 * getter方法注释
	 * 
	 * @param method
	 * @param introspectedTable
	 * @param introspectedColumn
	 */
	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		StringBuilder sb = new StringBuilder();
		method.addJavaDocLine("/**");
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			sb.append(" * 获取");
			sb.append(StringUtil.replaceAllMatch(introspectedColumn.getRemarks(), "\\[(.+?)\\]", ""));
			method.addJavaDocLine(sb.toString());
			method.addJavaDocLine(" *");
		}
		sb.setLength(0);
		sb.append(" * @return ");
		method.addJavaDocLine(" */");
	}

	/**
	 * setter方法注释
	 * 
	 * @param method
	 * @param introspectedTable
	 * @param introspectedColumn
	 */
	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		StringBuilder sb = new StringBuilder();
		method.addJavaDocLine("/**");
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			sb.append(" * 设置");
			sb.append(StringUtil.replaceAllMatch(introspectedColumn.getRemarks(), "\\[(.+?)\\]", ""));
			method.addJavaDocLine(sb.toString());
			method.addJavaDocLine(" *");
		}
		Parameter parm = method.getParameters().get(0);
		sb.setLength(0);
		sb.append(" * @param ");
		sb.append(parm.getName());
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			sb.append(" ");
			sb.append(introspectedColumn.getRemarks());
		}
		method.addJavaDocLine(sb.toString());
		method.addJavaDocLine(" */");
	}

	@Override
	public void addConfigurationProperties(Properties properties) {

	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {

	}

	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

	}

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {

	}

	@Override
	public void addComment(XmlElement xmlElement) {

	}

	@Override
	public void addRootComment(XmlElement rootElement) {

	}

}

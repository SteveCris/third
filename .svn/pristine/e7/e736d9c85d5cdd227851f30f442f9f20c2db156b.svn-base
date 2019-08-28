package com.mybatis.plugin;

import java.util.List;

import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;

public class GenPlugin extends PluginAdapter {

	private CommentGeneratorConfiguration commentCfg;

	public GenPlugin() {
		super();
	}

	public GenPlugin(CommentGeneratorConfiguration commentCfg) {
		super();
		this.commentCfg = commentCfg;
	}

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public void setContext(Context context) {
		super.setContext(context);
		// 设置默认的注释生成器
		commentCfg = new CommentGeneratorConfiguration();
		commentCfg.setConfigurationType(GenCommentGenerator.class.getCanonicalName());
		context.setCommentGeneratorConfiguration(commentCfg);
		// 支持oracle获取注释#114
		context.getJdbcConnectionConfiguration().addProperty("remarksReporting", "true");
	}

	public CommentGeneratorConfiguration getCommentCfg() {
		return commentCfg;
	}

	public void setCommentCfg(CommentGeneratorConfiguration commentCfg) {
		this.commentCfg = commentCfg;
	}

}

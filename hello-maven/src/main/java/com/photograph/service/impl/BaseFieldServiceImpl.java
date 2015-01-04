package com.photograph.service.impl;

import com.photograph.common.jackjson.Jackson;
import com.photograph.dao.BaseFieldMapper;
import com.photograph.pojo.BaseField;
import com.photograph.pojo.Criteria;
import com.photograph.service.BaseFieldService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

@Service
public class BaseFieldServiceImpl implements BaseFieldService {
	@Autowired
	private BaseFieldMapper baseFieldsMapper;

	private static final Logger logger = LoggerFactory.getLogger(BaseFieldServiceImpl.class);

	@Override
	public int countByExample(Criteria example) {
		int count = this.baseFieldsMapper.countByExample(example);
		logger.debug("count: {}", count);
		return count;
	}

	@Override
	public BaseField selectByPrimaryKey(String fieldId) {
		return this.baseFieldsMapper.selectByPrimaryKey(fieldId);
	}

	@Override
	public List<BaseField> selectByExample(Criteria example) {
		return this.baseFieldsMapper.selectByExample(example);
	}

	@Override
	public HashMap<String, String> selectAllByExample(Criteria example) {
		logger.info("开始读取系统默认配置");
		List<BaseField> list = this.baseFieldsMapper.selectByExample(example);
		HashMap<String, LinkedHashMap<String, String>> all = new HashMap<String, LinkedHashMap<String, String>>();
		LinkedHashMap<String, String> part = null;
		for (int i = 0; i < list.size(); i++) {
			BaseField baseFields = list.get(i);
			String key = baseFields.getField();
			if (all.containsKey(key)) {
				// 如果包含这个field，就加入它的值
				part = all.get(key);
				part.put(baseFields.getValueField(), baseFields.getDisplayField());
			} else {
				part = new LinkedHashMap<String, String>();
				part.put(baseFields.getValueField(), baseFields.getDisplayField());
				// 没有这个fiel，则新加入这个filed
				all.put(key, part);
			}
		}
		part = new LinkedHashMap<String, String>();
		for (Entry<String, LinkedHashMap<String, String>> entry : all.entrySet()) {
			String key = entry.getKey();
			HashMap<String, String> value = entry.getValue();
			// 为了eval('(${applicationScope.fields.sex})')这个单引号使用,替换所有的'，为\'
			//String val = Jackson.objToJson(value).replaceAll("\\'", "\\\\'");
			String val = StringEscapeUtils.escapeEcmaScript(Jackson.objToJson(value));
			logger.info(val);
			part.put(key, val);
		}
		logger.info("结束读取系统默认配置");
		return part;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public String saveFields(Criteria example) {
		BaseField fields = (BaseField) example.get("fields");

		int result = 0;
		if (fields.getFieldId() == null) {
			result = this.baseFieldsMapper.insertSelective(fields);
		} else {
			result = this.baseFieldsMapper.updateByPrimaryKeySelective(fields);
		}
		return result > 0 ? "01" : "00";
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public String delete(Criteria example) {
		String fieldId = example.getString("fieldId");
		int result = 0;
		// 删除自己
		result = this.baseFieldsMapper.deleteByPrimaryKey(fieldId);
		return result > 0 ? "01" : "00";
	}

}
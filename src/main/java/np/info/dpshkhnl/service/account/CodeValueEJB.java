/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import np.info.dpshkhnl.model.account.CodeValue;
import np.info.dpshkhnl.service.core.GenericDAO;

/**
 *
 * @author Dpshkhnl
 */
@Stateless
public class CodeValueEJB extends GenericDAO<CodeValue> {
	public CodeValueEJB() {
		super(CodeValue.class);

	}
    public List<CodeValue> findByDesignation(String designation) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("type", designation);		
		return super.findAllWithGivenCondition(CodeValue.FIND_BY_DESIGNATION, parameters);
	}


	public CodeValue findByCVId(int cvId_Passed) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("cvIdPassed", cvId_Passed);
		return super.findOneResult(CodeValue.FIND_BY_CV_ID, parameters);
	}

	public List<CodeValue> findByCVType(String cv_type_passed) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("cvTypePassed", cv_type_passed);
		return super.findAllWithGivenCondition(CodeValue.FIND_BY_CV_TYPE,
				parameters);
	}

	public List<CodeValue> listAllCodes() {
		List<CodeValue> result = findAll();
		return result;
	}

	public void createCodeValue(CodeValue codeValue) {
		save(codeValue);
	}

	public void updateCodeValue(CodeValue codeValue) {
		CodeValue persistedCodeValue = find(codeValue.getCvId());
		persistedCodeValue.setCvCode(codeValue.getCvCode());
		persistedCodeValue.setCvLbl(codeValue.getCvLbl());
		persistedCodeValue.setCvType(codeValue.getCvType());

		update(persistedCodeValue);
	}

	public void deleteCodeValue(CodeValue codeValue) {
		delete(codeValue.getCvId(), CodeValue.class);
	}

	public CodeValue findCodeValueByCVId(int cvId) {
		CodeValue codeValue = find(cvId);
		return codeValue;
	}

	public List<CodeValue> findCodeValueByCVType(String cvType) {
		List<CodeValue> result = findByCVType(cvType);
		return result;
	}
	public List<CodeValue> findCodeValueByCVType(String cvType,String label) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", cvType);
		map.put("label", label);
		return findAllWithGivenCondition(CodeValue.FIND_BY_CV_TYPE_LABEL, map);
	}

	public CodeValue getCodeValueByTypeAndLabel(String type, String label) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("label", label);
		return findOneResult(CodeValue.FIND_BY_CV_TYPE_LABEL, map);

	}

	public List<CodeValue> findCodeValueByLabel(String cvType) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", cvType);
		return findAllWithGivenCondition(CodeValue.FIND_BY_LABELS, map);

	}
}

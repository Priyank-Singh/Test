package com.reach.platform.customer.controller;

import static com.datami.leaf.common.HttpResponseUtils.prepareSuccessResponse;

import java.util.Map;
// hii, this is me

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.datami.leaf.common.GenericRes;
import com.reach.platform.common.dto.CustomerDTO;
import com.reach.platform.common.dto.LabelDTO;
import com.reach.platform.common.environment.Constants;
import com.reach.platform.common.utils.CommonUtility;
import com.reach.platform.provisioning.service.LabelService;

@RestController
@RequestMapping(Constants.REST_API_V0_CUST_SVC)
@SuppressWarnings("rawtypes")
public class LabelController {

	
	@Autowired
	private LabelService labelService;
	

	
	@RequestMapping(value = "/labels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<GenericRes> getLabels() throws Exception {
		return prepareSuccessResponse(labelService.getLabels());
	}
	
	
	@RequestMapping(value = "/labels/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<GenericRes> getLabelById(@PathVariable @NonNull String id) throws Exception {
		return prepareSuccessResponse(labelService.getLabelById(id));
	}
	
	
	@RequestMapping(value = "/labels/name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<GenericRes> getLabelByName(
			@RequestParam(value = "id", required = true) String Name) throws Exception {
		return prepareSuccessResponse(labelService.getLabelByName(Name.toLowerCase()));
	}
	
	
	@RequestMapping(value = "/addLabel", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<GenericRes> createLabel( @Valid @RequestBody LabelDTO labelTO) throws Exception {
		return prepareSuccessResponse(labelService.createLabel(labelTO));
	}
	
	@RequestMapping(value = "/labels/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<GenericRes> updateLabel(@Valid @RequestBody LabelDTO labelTO,
			@PathVariable @NonNull String id) throws Exception {
		return prepareSuccessResponse(labelService.updateLabel(labelTO, id, true));
	}
	
	@RequestMapping(value = "/labels/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<GenericRes> deleteLabel(@PathVariable @NonNull String id) throws Exception {
		labelService.deleteLabel(id);
		return prepareSuccessResponse("Label deleted Successfully");
	}
	
	@RequestMapping(value = "/force/labels/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<GenericRes> hardDeleteLabel(@PathVariable @NonNull String id) {
		labelService.hardDeleteLabel(id);
		return prepareSuccessResponse("Label deleted Successfully");
	}
	
	
	@RequestMapping(value = "/labels/hubspot/id/{hubSpotId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<GenericRes> getLabelByHubSpotId(@PathVariable @NonNull int hubSpotId) throws Exception {
		return prepareSuccessResponse(labelService.getLabelByHubSpotId(hubSpotId));
	}
	

	
	
	@RequestMapping(value = "/update/fields/label/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<GenericRes> updateLabelFields(@PathVariable @NonNull String id,
			@RequestBody @NonNull Map<String, Object> updateMap) throws Exception {
		labelService.updateLabelFields(id, updateMap);
		return prepareSuccessResponse(labelService.getLabelById(id));
	}
	
	@RequestMapping(value = "/update/fields/labels", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<GenericRes> updateLabelsField() throws Exception {
		labelService.updateLabelsField();
		return prepareSuccessResponse("Successfully updated!");
	}
	
	
	@RequestMapping(value = "/labels/unmap/{labelId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<GenericRes> unmapLabel(@PathVariable @NonNull String labelId) throws Exception {
		return prepareSuccessResponse(labelService.unmapLabel(labelId));
	}
	
	


}

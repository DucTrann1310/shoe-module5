package com.cg.service.companyService;

import com.cg.model.Company;
import com.cg.service.IGeneralService;

public interface ICompanyService extends IGeneralService<Company, Long> {

    Company findCompanyByName(String name);
}

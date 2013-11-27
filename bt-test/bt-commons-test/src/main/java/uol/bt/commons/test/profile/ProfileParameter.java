package uol.bt.commons.test.profile;

public enum ProfileParameter {
    state("idt_federation_unit"),
    city("idt_city"),
    area("idt_district"),
    jobFunction("idt_job_function"),
    jobIndustry("idt_job_industry"),
    gender("ind_sex"),
    zipcode("cod_postal_code"),
    maritalStatus("idt_marital_status"),
    education("idt_education"),
    householdIncome("idt_household_income"),
    interest("idt_interest"),
    birth("dat_birth"),
    personId("idt_person"),
    nickname("nam_login"),
    age("");

    final String uolCookieName;

    private ProfileParameter(String uolCookieName) {
        this.uolCookieName = uolCookieName;
    }

    public String getUolCookieName() {
        return uolCookieName;
    }
}

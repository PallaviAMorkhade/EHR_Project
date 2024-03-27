package com.hms.indus.util;

public enum Menu {
	   homepage("1"),database("2"),demograph("3"),reports("4"),verification("5"),hra("6"),others("7"),reminders("8"),dataManagement("9"),
	   interaction("10"),accountManagement("11"),userAccessManagement("12"),HRATypeMaster("13"),HRAQuestionMaster("14"),
	   parameterMaster("15"),parameterValues("16"),unitMaster("17"),rejectMaster("18"),templateMaster("19"),smsTemplateMaster("20"),
	   actionMaster("21"),subActionMaster("22"),visitTypeMaster("23"),clientRegistrationPage("24"),
	   feedTypeMaster("25"),healthFeedMaster("26"),uploadVideoMaster("27"),linkMaster("28"),analysis("29"),
	   clientIntegrationPage("30"),contentArticlePage("31"),bunchUploadPage("32"),categoryMaster("33"),
	   subCategoryMaster("34"),contentPreview("35"),parameterDetails("36"),hraDetails("37"),frequencyMaster("38"),
	   analysisCommentMaster("39"),workManagement("40"),contactHubNew("41"),getBeneficiary("42"),packageTestRelation("43");
	   private String id;
	   Menu(String id) {
	      this.id = id;
	   }
	   public String getId() {
	      return id;
	   } 
}

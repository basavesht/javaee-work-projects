package com.tcs.Payments.ms360Utils;

import java.util.Arrays;
import java.util.List;

/**
 * @author : 224703
 * **************   Revision History ************************
 * Modified-By |  Modified-Date |  Project-Phase  | Changes
 *    224703       23-09-2011        P3-B            PLA  
 * **********************************************************
 */
public class MSSystemDefaults {

	public final static String MSACC_HELD_AT             = "MSSB";
	public final static String NOT_APPLICABLE            = "N/A";

	public final static String DEFAULT_ACC_TEXT          = "Select an Account";
	public final static String DEFAULT_SPOKE_TO_TEXT     = "Please Select..."; 
	public final static String DEFAULT_TXN_TYPE          = "Select a Transfer Type"; 

	//JSON Key for the other MS account selection ... (Provided by MS360 Framework)
	public final static String JSON_ACNT_KEY             = "Account";
	public final static String JSON_OFFICE_KEY           = "Office";
	public final static String JSON_FA_KEY               = "FA";
	public final static String JSON_KEYACNT_KEY          = "KeyAccount";
	public final static String JSON_NAME_KEY             = "DisplayName";

	public final static String DEFAULT_DATE_TXT          = "MM/DD/YYYY"; 

	//Page Id's for MS360 Screens ...
	public final static String INTERNAL_TRANSFERS        = "9901"; 
	public final static String EXTERNAL_TRANSFERS        = "9904"; 
	public final static String CTC_SEARCHES              = "9908"; 
	public final static String CTC_APPROVALS             = "9911"; 
	public final static String CHECK_REQUESTS            = "9912"; 
	public final static String BRANCH_PRINT_MANAGER      = "4401"; 

	//Page Name's for MS360 Screens ...
	public final static String BPM_DESC                  = "BPM"; 

	//Default Error string to be prefixed for the bottom line web service...
	public final static String BT_ERRORCODE_PREFIX       = "BottomLine_Err";

	//Account view mailing address category...
	public final static String ACNT_VIEW_MAIL_ADRSS_CATEGORY    = "MAILNG";
	public final static String ACNT_VIEW_PLATING_ADRSS_CATEGORY = "REGADR";
	public final static String ACNT_VIEW_CLIENT_RELATIONSHIP    = "CLIENT";

	//Account Plating default entity Names...
	public final static String AUTH_ENTITY_OTHER               = "Other";
	public final static String AUTH_ENTITY_OTHER_VAL           = "Enter a name";

	//Authorization Mode at transaction level...
	public final static String AUTH_VERBAL  = "Verbal";
	public final static String AUTH_SIGNED  = "Signed";

	//Enable/Disable MS360 24*7 Functionality based on transaction type...
	public final static boolean ENABLE_24X7    = true;
	public final static List    PermittedTxnsList_24x7  = Arrays.asList(TxnTypeCode.INTERNAL);

	//Third Party Indicator flag...
	public final static String THRD_PARTY_Y    = "Yes";
	public final static String THRD_PARTY_N    = "No";

	//PLA Display Text
	public final static String LOAN_ACC_PREFIX_TEXT    = "PLA";
	public final static String LOAN_ACC_IND_REGX       = "PLA~";
	public final static String LOANACC_HELD_AT         = "MSBNA";
	public final static String LOANACC_DTLS            = "PLA";
}

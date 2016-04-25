package com.example.helpsrchexpand;
/**
 * Created by maidul.islam on 21/11/15.
 */

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
//public class HelpFaqDetails extends ActionBarActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {
public class HelpFaqDetails extends ActionBarActivity /*implements SearchView.OnQueryTextListener, SearchView.OnCloseListener*/ {

	int temp_pos;
	// Search EditText
	EditText inputSearch;
	private SearchView search;
	private MyListAdapter listAdapter;
	private ExpandableListView myList;
	private ArrayList<Continent> continentList = new ArrayList<Continent>();
	private ArrayList<Continent> continentList1 = new ArrayList<Continent>();
	private int lastExpandedPosition = -1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help_faq);
//		MyApplication.getInstance().trackEvent("Help Deatils", "Launch", "onCreate");
		inputSearch = (EditText) findViewById(R.id.inputSearch);

		/**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				// When user changed the Text
//				HelpFaqDetails.this.adapter.getFilter().filter(cs);
				String string2 = String.valueOf(cs);
				Log.d("HELP ADAPTER", "string2 "+string2);
				listAdapter.filterData(string2);

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			
				Log.d("HELP ADAPTER after ", "inputSearch  "+inputSearch.getText().toString());
			}
		});
		LinearLayout linearLayoutCall = (LinearLayout) findViewById(R.id.lay_help_call);
		LinearLayout linearLayoutMessage = (LinearLayout) findViewById(R.id.lay_help_txt_message);
		linearLayoutCall.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_DIAL);
				callIntent.setData(Uri.parse(getString(R.string.cointribe_dial_no)));
				startActivity(callIntent);
			}
		});
		linearLayoutMessage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent sendIntent = new Intent(Intent.ACTION_SEND);
				sendIntent.setType("message/rfc822");
				sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.cointribe_emial_id)});
				sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Enquiry for New Loan");
				sendIntent.putExtra(Intent.EXTRA_TEXT, "Reply me ASAP");
				if (sendIntent.resolveActivity(getPackageManager()) != null) {
					startActivity(Intent.createChooser(sendIntent, "Send mail..."));
				} else {
					Toast.makeText(HelpFaqDetails.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
				}
			}
		});
//		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//		search = (SearchView) findViewById(R.id.search);
//		search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//		search.setIconifiedByDefault(true);
//		search.setOnQueryTextListener(this);
//		search.setOnCloseListener(this);
//
//		search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				if (!hasFocus) {
//					search.setIconified(true);
//				}
//			}
//		});

		// display the list
		displayList();
		// expand all Groups
//		expandAll();
//		expandableListViewListners();
	}

//	private void expandableListViewListners() {
//		// TODO Auto-generated method stub
//		myList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//	            @Override
//	            public void onGroupExpand(int groupPosition) {
//
//	                if (lastExpandedPosition != -1
//	                        && groupPosition != lastExpandedPosition) {
//	                	myList.collapseGroup(lastExpandedPosition);
//
//	                }
//	                lastExpandedPosition = groupPosition;
//	                myList.setSelection(groupPosition);
//
//	            }
//	        });
//	    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

	// method to expand all groups
	private void expandAll() {
		int count = listAdapter.getGroupCount();
		for (int i = 0; i < count; i++) {
			myList.expandGroup(i);
		}
	}

	// method to expand all groups
	private void displayList() {

		// display the list

		Intent intent = getIntent();
        temp_pos = intent.getIntExtra("int_value", 0); // here
        System.out.println("temp_pos " + temp_pos);
        loadSomeData();
        if (temp_pos == 0) {
//        	loadSomeData();
        	List<Continent> sublist = continentList.subList(0, 8);
        	for (Continent element : sublist) {
        	    System.out.println("Element: " + element);
        	    continentList1.add(element);
        	    Constant.getAllContinent1=continentList1;
        	}


        }
        if (temp_pos == 1) {
//            prepareListData1();
        	List<Continent> sublist = continentList.subList(8, 18);
        	for (Continent element : sublist) {
        	    System.out.println("Element: " + element);
        	    continentList1.add(element);
        	    Constant.getAllContinent1=continentList1;
        	}
        }
        if (temp_pos == 2) {
//            prepareListData2();
        	List<Continent> sublist = continentList.subList(18, 19);
        	for (Continent element : sublist) {
        	    System.out.println("Element: " + element);
        	    continentList1.add(element);
        	    Constant.getAllContinent1=continentList1;
        	}
        }
        if (temp_pos == 3) {
//            prepareListData3();
        	List<Continent> sublist = continentList.subList(19, 26);
        	for (Continent element : sublist) {
        	    System.out.println("Element: " + element);
        	    continentList1.add(element);
        	    Constant.getAllContinent1=continentList1;
        	}
        }



		// get reference to the ExpandableListView
		myList = (ExpandableListView) findViewById(R.id.expandableList);
		// create the adapter by passing your ArrayList data
		listAdapter = new MyListAdapter(HelpFaqDetails.this, continentList1);
		// attach the adapter to the list
		myList.setAdapter(listAdapter);

	}

	private void loadSomeData() {{

		ArrayList<BeanHelp> countryList = new ArrayList<BeanHelp>();
		/*Part 1
		 * QUESTION 1
		 * */
		BeanHelp country = new BeanHelp("We help businesses and individuals in getting short-term, flexible collateral free loans in a simple and efficient manner. " +
				"Whether you need funds to manage some personal expenses or are a proud owner of a business with frequent working capital needs, " +
                "loans through CoinTribe will provide you convenient access to funds to invest in growth, cover expenses, meet cash flow requirements, " +
                "pay suppliers or spend on personal requirements.'");
		countryList.add(country);
		Continent continent = new Continent("Is a CoinTribe Loan right for my business or personal use?", countryList);
		continentList.add(continent);

		/*Part 1
		 * QUESTION 2
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("Currently, you can draw loans ranging from INR 30 thousand to INR 20 lacs depending on your income, repayment capacity, " +
				"and rating. The loans are offered for a repayment tenure of up to 60 months.");
		countryList.add(country);
		continent = new Continent("How much can I borrow and for how long?", countryList);
		continentList.add(continent);
		/*Part 1
		 * QUESTION 3
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("The rate offered depends on your credit assessment and thereafter the rate offered by our lending partner(s).");
		countryList.add(country);
		continent = new Continent("What is the rate offered?", countryList);
		continentList.add(continent);

		/*Part 1
		 * QUESTION 4
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("CoinTribe does not charge any processing fee from you. However, " +
				"in case the lender charges a processing fee, it will be mentioned clearly for you to check. " +
				"Unless mentioned specifically you do not need to pay any fees. There will 'never’ be any hidden charges.");
		countryList.add(country);
		continent = new Continent("Is there any processing fee I need to pay?", countryList);
		continentList.add(continent);

		/*Part 1
		 * QUESTION 5
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("Anyone, above 18 years of age, who is in need of capital can " +
				"apply though CoinTribe for a loan. You could be a salaried individual " +
				"or running your enterprise.");
		countryList.add(country);
		continent = new Continent("Who can apply?", countryList);
		continentList.add(continent);

		/*Part 1
		 * QUESTION 6
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("Of course! It’s so convenient and fast to draw a loan through CoinTribe that we recommend " +
				"you to draw loan as per your need and draw out more if your need increases. You need not draw higher" +
				" amounts as buffer to avoid the complication of applying for a new loan any more.");
		countryList.add(country);
		continent = new Continent("I already have one loan running through CoinTribe, can I apply again and how?", countryList);
		continentList.add(continent);

		/*Part 1
		 * QUESTION 7
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("CoinTribe is committed to serve you during the entire Loan Cycle. However, as a borrower, " +
				"you have the flexibility to approach the lender directly as well. The name of the lender will be " +
                "clearly disclosed in your loan agreement..'");
		countryList.add(country);
		continent = new Continent("Who will service me during my loan term?", countryList);
		continentList.add(continent);
		/*Part 1
		 * QUESTION 8
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("We are open to all financial institutions to lend through us." +
				" The specific lender who has offered to lend you money will be clearly mentioned in your loan agreement.");
		countryList.add(country);
		continent = new Continent("Who will offer loan to me?", countryList);
		continentList.add(continent);


		/*Part 2
		 * QUESTION 1
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("Application can be done through our Easy, Secure, " +
				"Fast mobile app or Webpage. To apply now, Login/Register and click Apply for new Loan");
		countryList.add(country);
		continent = new Continent("How can I apply?", countryList);
		continentList.add(continent);

		/*Part 2
		 * QUESTION 2
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("We share your requirement with all potential lenders on our platform. While you apply through just one window," +
				" we interact on your behalf with all of them to ensure that you do not undergo the hassle of multiple points of communication." +
                " Once the loan is sanctioned, you’ll get details about your lender and you can choose to contact them directly." +
                " However, we’ll continue to remain committed to you – please feel free to reach out to us for any queries or concerns " +
                "and we’d be happy to address them.");
		countryList.add(country);
		continent = new Continent("Do I need to interact with the lender separately?", countryList);
		continentList.add(continent);
		/*Part 2
		 * QUESTION 3
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("Only with potential lenders. Your privacy is of utmost importance to us and only information necessary " +
				"for loan sanction and required as per regulatory/statutory guidelines will be shared with the lenders.");
		countryList.add(country);
		continent = new Continent("Will the data provided by me shared with anyone?", countryList);
		continentList.add(continent);

		/*Part 2
		 * QUESTION 4
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("CoinTribe follows stringent data protection guidelines and during the entire process of loan appraisal " +
				"and sanction none of the account IDs or passwords are stored on the CoinTribe system neither is there any manual " +
				"intervention in the process. The IDs and Passwords are used for automatic data extraction from the bank systems. " +
				"If however for any reason you don’t wish to enter your ID or password, you can also download your bank statement " +
				"from your bank’s website and upload the same on our mobile application/website.");
		countryList.add(country);
		continent = new Continent("Is it safe to share information like bank account log-in?", countryList);
		continentList.add(continent);

		/*Part 2
		 * QUESTION 5
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("During the loan application we will request you for a National Automated Clearing House (NACH) " +
				"mandate form to be filled authorizing the lender to debit your bank account for interest and principal payments " +
				"for the loan. Your account will be auto debited on a monthly basis (unless specified otherwise). In case the " +
				"NACH mandate provided by you is not accepted by your banker, we may bank the EMI/Security cheques collected " +
				"from you.\n" +
				"\n" +
				"Either lender or we would keep you informed through E-Mail/SMS to enable you to ensure availability of " +
				"funds for such repayments.\n" +
				"\n" +
				"In case you would like to repay earlier, you can pre-pay your loan in part or in full any time during " +
				"the balance term of the loan subject to the terms and conditions against your loan. Your tenure or " +
				"EMI amount will be suitably adjusted in case of a part pre-payment. Pre-payment charges if applicable" +
				" will be charges to you against such pre-payment.");
		countryList.add(country);
		continent = new Continent("How do I repay my loan?", countryList);
		continentList.add(continent);

		/*Part 2
		 * QUESTION 6
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("Yes, you can alter your loan request while making an application. In case you face any issues with the same, " +
				"please feel free to contact us and we’d be happy to help'");
		countryList.add(country);
		continent = new Continent("Can I alter my loan request and how?", countryList);
		continentList.add(continent);

		/*Part 2
		 * QUESTION 7
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("We conduct an assessment of your needs and the application within seconds of you providing all" +
				" the information. In case your loan is approved by a lending partner with online integration with us, " +
				"you’ll know the status immediately. Else, we will follow-up with the lenders on our platform to get you" +
				" the result of your application as soon as possible. We will be integrating online with more and more " +
				"lenders to make this process instant in most cases.");
		countryList.add(country);
		continent = new Continent("When & how will I get to know about the status of my application?", countryList);
		continentList.add(continent);
		/*Part 2
		 * QUESTION 8
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("After you’ve keyed in basic personal details about you and your employer/business " +
				"along with your loan requirements you will be required to share your Aadhaar details , " +
				"Bank Statement, Business KYC (if required) and link your Social Accounts (like Facebook. Linkedin, " +
				"etc). Usually this is sufficient to process a loan request, however in exceptional cases additional " +
				"information may be required, in that case we would approach you.");
		countryList.add(country);
		continent = new Continent("What are the Documents/Information’s required for applying for a loan?", countryList);
		continentList.add(continent);
		/*Part 2
		 * QUESTION 9
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("The date of EMI debit varies across lenders. " +
				"It usually depends on the date of disbursement or standard cycles followed by lenders.");
		countryList.add(country);
		continent = new Continent("When will my EMI start getting debited from my account?", countryList);
		continentList.add(continent);
		/*Part 2
		 * QUESTION 10
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("If the FAQs did not help you with your query, please reach out to us at Toll " +
				"Free no 18008439240 [Monday to Friday - 9:00 AM to 7:00 PM and Saturday - 9:00 AM to 2:00 PM] " +
				"or email us at support@cointribe.com and we would be happy to assist you.");
		countryList.add(country);
		continent = new Continent("I'm struck during theapplication process, what should I do?", countryList);
		continentList.add(continent);

		/*Part 3
		 * QUESTION 1
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("Once the auto-debit NACH mandate is provided by you/ collected from you," +
				" it will take up to 7 days for the disbursements to be credited into your account. " +
                "To make a faster disbursement we might also collect EMI cheques from you.'");
		countryList.add(country);
		continent = new Continent("If my loan is sanctioned, when will I get the disbursement?", countryList);
		continentList.add(continent);

		/*Part 4
		 * QUESTION 1
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("The amortization schedule or the repayment schedule will be provided to you along with the welcome kit/ " +
				"document docket. For an account statement, you are requested to contact us – we’ll enable you to download the statements" +
				" on your own soon.");
		countryList.add(country);
		continent = new Continent("How will I get Statement of Account/ Amortisation Schedule", countryList);
		continentList.add(continent);

		/*Part 4
		 * QUESTION 2
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("You can make changes to your profile while applying for a new loan. Meanwhile if you are still " +
				"serving a loan and would like us to change your contact details, please contact us and we’d be happy to update our records.");
		countryList.add(country);
		continent = new Continent("How can I make changes to my profile??", countryList);
		continentList.add(continent);
		/*Part 4
		 * QUESTION 3
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("For most loans through CoinTribe, after the first EMI (or one month from disbursement whichever is higher)," +
				" you can pre-pay your loan in part any time whenever you like. No prepayment interest or penalty will be charged for " +
                "the same. Your tenure will be suitably adjusted on account of the pre-payment. In case the prepayment terms differ for " +
                "lenders or products you’ll be notified of the same before confirmation of your loan.'");
		countryList.add(country);
		continent = new Continent("How and when can I make a part prepayment and will it attract any charges?", countryList);
		continentList.add(continent);

		/*Part 4
		 * QUESTION 4
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("After the first EMI (or one month from disbursement whichever is higher), you can foreclose your loan with NIL " +
				"foreclosure charges, at any point of time by paying the total outstanding. In case the foreclosure terms differ for " +
                "lenders or products you’ll be notified of the same before confirmation of your loan.'");
		countryList.add(country);
		continent = new Continent("How do I foreclose my loan and what are the foreclosure charges?", countryList);
		continentList.add(continent);

		/*Part 4
		 * QUESTION 5
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("The No Dues Certificate will be directly mailed to you by the respective lender(s).'");
		countryList.add(country);
		continent = new Continent("How do I get my No Dues Confirmation once my loan is closed?", countryList);
		continentList.add(continent);

		/*Part 4
		 * QUESTION 6
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("The EMI amount will be informed while making disbursements." +
				" This information is also a part of your welcome kit and/ or the document docket.");
		countryList.add(country);
		continent = new Continent("When is my next EMI due and what is the amount and debit date?", countryList);
		continentList.add(continent);

		/*Part 4
		 * QUESTION 7
		 * */
		countryList = new ArrayList<BeanHelp>();
		country = new BeanHelp("For any missed payments, you may contact CoinTribe for executing a NACH debit in between the" +
				" EMI cycle too. CoinTribe will provide proactive SMS/ Email alerts for amounts to be debited, and also " +
                "reminders in case any payments are missed.");
		countryList.add(country);
		continent = new Continent("How can I make my overdue payment?", countryList);
		continentList.add(continent);



		Constant.getAllContinent=continentList;
	}
	}

//	@Override
//	public boolean onClose() {
//		listAdapter.filterData("");
////		expandAll();
//		return false;
//	}
//
//	@Override
//	public boolean onQueryTextChange(String query) {
////		listAdapter.filterData(query);
////		expandAll();
//		return false;
//	}
//
//	@Override
//	public boolean onQueryTextSubmit(String query) {
//		listAdapter.filterData(query);
////		expandAll();
//		return false;
//	}

	//	@Override
//	public void onBackPressed() {
//		super.onBackPressed();
//		hideKeyboard();
//	}
	private void hideKeyboard(){
		InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		onBackPressed();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			hideKeyboard();
		}

		return super.onKeyDown(keyCode, event);
	}

	public void onBackPressed() {
		//do something

		if (!search.isIconified()) {
			search.setIconified(true);
		} else {
			super.onBackPressed();
		}
		onSupportNavigateUp();
		finish();
		return;
	}
}

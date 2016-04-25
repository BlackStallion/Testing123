package com.example.helpsrchexpand;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by maidul.islam on 21/11/15.
 */

public class Helpmain extends ActionBarActivity {

    // Listview Adapter
    ArrayAdapter<String> adapter;
    // Search EditText
    EditText inputSearch;
    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;
    List<String> stringList;
    ViewHolderItem viewHolder;
    boolean bool_help = true;
    //	String products[] = {"I'm thinking of applying for a Loan", "I'm applying for Loan",
//			"I'm waiting for my disbursement", "I'm a CoinTribe customer"};
    // List view
    private ListView lv;
    private SearchView search;
    // Listview Data
    private DetailsAdapter detailsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_main);

        lv = (ListView) findViewById(R.id.list_help_info);
        stringList = new ArrayList<String>();
        stringList.add("I'm thinking of applying for a Loan");
        stringList.add("I'm applying for Loan");
        stringList.add("I'm waiting for my disbursement");
        stringList.add("I'm a CoinTribe customer");

	        inputSearch = (EditText) findViewById(R.id.inputSearch);

        // Adding items to listview
//	        adapter = new ArrayAdapter<String>(this, R.layout.list_item_help, R.id.product_name, products);
//	        lv.setAdapter(adapter);


        if (bool_help) {
            onSetListItems();
        }

        bool_help = false;
        inputSearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Helpmain.this.detailsAdapter.getFilter().filter(inputSearch.getText().toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});

//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        search = (SearchView) findViewById(R.id.search);
//        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        search.setIconifiedByDefault(true);
//        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    search.setIconified(true);
//                }
//            }
//        });
//        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Helpmain.this.detailsAdapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Helpmain.this.detailsAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        search.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                Helpmain.this.detailsAdapter.getFilter().filter("");
//                return false;
//            }
//        });

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

                // Verify that there are applications registered to handle this intent
                // (resolveActivity returns null if none are registered)
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(sendIntent, "Send mail..."));
                } else {
                    Toast.makeText(Helpmain.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Helpmain.this, HelpFaqDetails.class);
                intent.putExtra("int_value", position);
                startActivity(intent);
            }
        });

    }

    private void onSetListItems() {
        detailsAdapter = new DetailsAdapter(Helpmain.this,
                R.layout.list_item_help, stringList);
        lv.setAdapter(detailsAdapter);

        detailsAdapter.notifyDataSetChanged();
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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

    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // User has pressed Back key. So hide the keyboard
            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//			mgr.hideSoftInputFromWindow(this.getWindowToken(), 0);
            mgr.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            // TODO: Hide your view as you do it in your activity
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            // Eat the event
            return true;
        }
        return false;
    }

    static class ViewHolderItem {
        public TextView txt_name;

    }

    private class DetailsAdapter extends
            ArrayAdapter<String> {

        Activity activity;
        private int resource;

        //        Context _context;
        public DetailsAdapter(Activity activity, int _ResourceId,
                              List<String> _items) {
            super(activity, _ResourceId, _items);
//            this.resource = _ResourceId;
            this.activity = activity;
            // this.activity=activity;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item_help, null);
                viewHolder = new ViewHolderItem();

                viewHolder.txt_name = (TextView) convertView.findViewById(R.id.product_name);

//                viewHolder.txtAmout.setText(Constants.formatter.format(Double.valueOf(beanMyCompleteLoan.getLoanAmount())));

                convertView.setTag(viewHolder);


            } else {
                viewHolder = (ViewHolderItem) convertView.getTag();

            }

            viewHolder.txt_name.setText(stringList.get(position));

            return convertView;
        }
    }


}

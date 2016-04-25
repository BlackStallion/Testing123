package com.example.helpsrchexpand;
/**
 * Created by maidul.islam on 21/11/15.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class MyListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<Continent> continentList;
	private ArrayList<Continent> originalList;

	public MyListAdapter(Context context, ArrayList<Continent> continentList) {
		this.context = context;
		this.continentList = new ArrayList<Continent>();
		this.continentList.addAll(continentList);
		this.originalList = new ArrayList<Continent>();
		this.originalList.addAll(Constant.getAllContinent);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		ArrayList<BeanHelp> countryList = continentList.get(groupPosition).getCountryList();
		return countryList.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {

		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {

		final BeanHelp country = (BeanHelp) getChild(groupPosition, childPosition);
		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.child_row, null);
		}

		TextView name = (TextView) view.findViewById(R.id.name);

		final String strAnsData = country.getName().trim();
		final String strPhoneNum = "18008439240";
		final String strEmailId = "support@cointribe.com";

		if (strAnsData.toLowerCase().contains(strPhoneNum.toLowerCase())
				&& strAnsData.toLowerCase().contains(strEmailId.toLowerCase())) {

			int idx = strAnsData.indexOf(strPhoneNum);
			String a = strAnsData.substring(0, idx);
			// String b = strAnsData.substring((a+strPhoneNum).length(),
			// strAnsData.length());

			int idx1 = strAnsData.indexOf(strEmailId);
			String a1 = strAnsData.substring((a + strPhoneNum).length(), idx1);
			String b1 = strAnsData.substring((a + strPhoneNum + a1 + strEmailId).length(), strAnsData.length());

			final SpannableStringBuilder sb = new SpannableStringBuilder(a + strPhoneNum + a1 + strEmailId + b1);

			// Span to set text color to some RGB value
			final ForegroundColorSpan fcs = new ForegroundColorSpan(Color.BLACK);

			// Span to make text bold
			final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);

			// Set the text color for first 4 characters
			sb.setSpan(fcs, a.length(), (a + strPhoneNum).length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

			// make them also bold
			sb.setSpan(bss, a.length(), (a + strPhoneNum).length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

			// Span to set text color to some RGB value
			final ForegroundColorSpan fcs1 = new ForegroundColorSpan(Color.BLACK);

			// Span to make text bold
			final StyleSpan bss1 = new StyleSpan(android.graphics.Typeface.BOLD);

			// Set the text color for first 4 characters
			sb.setSpan(fcs1, (a + strPhoneNum + a1).length(), (a + strPhoneNum + a1 + strEmailId).length(),
					Spannable.SPAN_INCLUSIVE_INCLUSIVE);

			// make them also bold
			sb.setSpan(bss1, (a + strPhoneNum + a1).length(), (a + strPhoneNum + a1 + strEmailId).length(),
					Spannable.SPAN_INCLUSIVE_INCLUSIVE);

			ClickableSpan clickableSpan = new ClickableSpan() {
				@Override
				public void onClick(View widget) {
					try {
						Intent callIntent = new Intent(Intent.ACTION_DIAL);
						callIntent.setData(Uri.parse("tel:18008439240"));
						context.startActivity(callIntent);
					} catch (android.content.ActivityNotFoundException ex) {
						Toast.makeText(context, "Alas!! Your device does not support phone out going facility",
								Toast.LENGTH_SHORT).show();
					}
				}
			};
			ClickableSpan clickableSpan1 = new ClickableSpan() {
				@Override
				public void onClick(View widget) {
					Intent sendIntent = new Intent(Intent.ACTION_SEND);
					sendIntent.setType("message/rfc822");
					sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "contactus@cointribe.com" });
					sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Enquiry for New Loan");
					sendIntent.putExtra(Intent.EXTRA_TEXT, "Reply me ASAP");
					if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
						context.startActivity(Intent.createChooser(sendIntent, "Send mail..."));
					} else {
						Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
					}
				}
			};

			sb.setSpan(clickableSpan, a.length(), (a + strPhoneNum).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			sb.setSpan(clickableSpan1, (a + strPhoneNum + a1).length(), (a + strPhoneNum + a1 + strEmailId).length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			name.setText(sb);
			name.setMovementMethod(LinkMovementMethod.getInstance());

		} else {
			name.setText(country.getName().trim());
		}
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {

		ArrayList<BeanHelp> countryList = continentList.get(groupPosition).getCountryList();
		return countryList.size();

	}

	@Override
	public Object getGroup(int groupPosition) {
		return continentList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return continentList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isLastChild, View view, ViewGroup parent) {

		Continent continent = (Continent) getGroup(groupPosition);
		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.group_row, null);
		}

		TextView heading = (TextView) view.findViewById(R.id.heading);
		heading.setText(continent.getName().trim());

		return view;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public void filterData(String strSearch) {

		strSearch = strSearch.toLowerCase();
		Log.v("MyListAdapter", String.valueOf(continentList.size()));
		continentList.clear();

		if (strSearch.isEmpty()) {
			continentList.addAll(Constant.getAllContinent1);
		} else {

			String usernameArr[] = strSearch.split(" ");
//			for (int i = 0; i < usernameArr.length; i++) {
//				String ss = usernameArr[i];
//				Log.d("check", "query " + ss);
//			}

			for (String query : usernameArr) {
				for (Continent continent : originalList) {
					ArrayList<BeanHelp> countryList = continent.getCountryList();
					ArrayList<BeanHelp> newList = new ArrayList<BeanHelp>();
					for (BeanHelp country : countryList) {
						if (country.getName().toLowerCase().contains(query)||continent.getName().toLowerCase().contains(query)) {
							System.out.println("country.getName() : " + country.getName());
							System.out.println("query : " + query);
							newList.add(country);
						}
						
					}
					if (newList.size() > 0) {
						Continent nContinent = new Continent(continent.getName(), newList);
						
						Set set = new TreeSet(new Comparator() {
							public int compare(Continent o1, Continent o2) {
								if(o1.getName().equalsIgnoreCase(o2.getName())){
					        		return 0;
					        	}		
					       	return 1;
							}

							@Override
							public int compare(Object lhs, Object rhs) {
								// TODO Auto-generated method stub
								return 0;
							}
						});
						set.add(nContinent);
						
						System.out.println("continent.getName() : " + continent.getName());
						continentList.addAll(set);
						
					}
				}

			}

			// for (Continent continent : originalList) {
			//
			// ArrayList<BeanHelp> countryList = continent.getCountryList();
			// ArrayList<BeanHelp> newList = new ArrayList<BeanHelp>();
			// for (BeanHelp country : countryList) {
			// if (country.getName().toLowerCase().contains(query)) {
			// newList.add(country);
			// }
			// }
			// if (newList.size() > 0) {
			// Continent nContinent = new Continent(continent.getName(),
			// newList);
			// continentList.add(nContinent);
			// }
			// }

		}

		Log.v("MyListAdapter", String.valueOf(continentList.size()));
		notifyDataSetChanged();

	}

}

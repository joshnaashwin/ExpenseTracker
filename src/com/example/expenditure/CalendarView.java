package com.example.expenditure;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarView extends Activity {
    Context context=this;
	public GregorianCalendar month, itemmonth;// calendar instances.

	public CalendarAdapter adapter;// adapter instance
	public Handler handler;// for grabbing some event values for showing the dot
							// marker.
	public ArrayList<String> items; // container to store calendar items which
									// needs showing the event marker
	ArrayList<String> event;
	LinearLayout rLayout;
	ArrayList<String> date;
	ArrayList<String> desc;
	public static String current_day;
	SQLiteDatabase db;
	DbHelper_exp dbhelper_exp;
	Cursor cs1,cs2,cs;
	Button current_month;
	Button current_year_current;
	TextView current_month_tv;
	TextView current_year_tv;
	int sum_value=0;
	String summm;
	int n;
	int sum_value_year=0;
	String summm_year;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
		Locale.setDefault(Locale.US);
		dbhelper_exp=new DbHelper_exp(this, "My_Exp", null, 1);
		db=dbhelper_exp.getWritableDatabase();
		current_month=(Button)findViewById(R.id.button_current_month);
		current_year_current=(Button)findViewById(R.id.button_year);
		current_month_tv=(TextView)findViewById(R.id.textView_current_month);
		current_year_tv=(TextView)findViewById(R.id.textView_current_year);

		rLayout = (LinearLayout) findViewById(R.id.text);
		month = (GregorianCalendar) GregorianCalendar.getInstance();
		int month_month=month.get(GregorianCalendar.MONTH);
		Log.d("month_month",""+ month_month);
		int month_m=month_month+1;
		
		
	String current_month_month = null;
		 
		
		
		if(month_m<10)
		{
			current_month_month="0"+month_m;
		}
		final String m_month=current_month_month;
		//Log.d("current_month_month",m_month);
		int year_year=month.get(GregorianCalendar.YEAR);
		Log.d("year_year",""+year_year);
		
		
		final String y_year=""+year_year;
		Log.d("y_year",y_year);
		
		
		int date_date=month.get(GregorianCalendar.DATE);
		Log.d("date_date",""+date_date);
		
		final String d_date=""+date_date;
		
		itemmonth = (GregorianCalendar) month.clone();

		items = new ArrayList<String>();

		adapter = new CalendarAdapter(this, month);
		//TextView day_View=adapter.dayView;
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		final GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(adapter);

		handler = new Handler();
		handler.post(calendarUpdater);

		 final TextView title = (TextView)findViewById(R.id.title);
		title.setText(android.text.format.DateFormat.format(" MMMM yyyy", month));
		
		
		RelativeLayout previous = (RelativeLayout) findViewById(R.id.previous);

		previous.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setPreviousMonth();
				refreshCalendar();
			}
		});

		RelativeLayout next = (RelativeLayout) findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setNextMonth();
				refreshCalendar();

			}
		});
//String item_main=MainActivity.items;


		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// removing the previous view if added
				current_day=  (String) gridview.getItemAtPosition(position);
				Log.d("sss",current_day);
				 n=current_day.length();
					String year_current=current_day.substring(0, n-6);
					String month_current=current_day.substring(5,n-3);
					//String month_current=current_day.substring(start);
					Log.d("date_year", year_current);
					Log.d("date_month",month_current);
				Intent in=new Intent(CalendarView.this,MainActivity.class);
				Bundle b=new Bundle();
				b.putString("date", current_day);
				in.putExtras(b);
				startActivity(in);
				
				
				
				
				
				
				if (((LinearLayout) rLayout).getChildCount() > 0) {
					((LinearLayout) rLayout).removeAllViews();
					
					
					
					
					
				}
				desc = new ArrayList<String>();
				date = new ArrayList<String>();
				((CalendarAdapter) parent.getAdapter()).setSelected(v);
				String selectedGridDate = CalendarAdapter.dayString
						.get(position);
				String[] separatedTime = selectedGridDate.split("-");
				String gridvalueString = separatedTime[2].replaceFirst("^0*",
						"");// taking last part of date. ie; 2 from 2012-12-02.
				int gridvalue = Integer.parseInt(gridvalueString);
				Log.d("qqqqqq", gridvalueString);
				// navigate to next or previous month on clicking offdays.
				if ((gridvalue > 10) && (position < 8)) {
					setPreviousMonth();
					refreshCalendar();
				} else if ((gridvalue < 7) && (position > 28)) {
					setNextMonth();
					refreshCalendar();
				}
				((CalendarAdapter) parent.getAdapter()).setSelected(v);

				for (int i = 0; i < Utility.startDates.size(); i++) {
					if (Utility.startDates.get(i).equals(selectedGridDate)) {
						desc.add(Utility.nameOfEvent.get(i));
					}
				}

				if (desc.size() > 0) {
					for (int i = 0; i < desc.size(); i++) {
						TextView rowTextView = new TextView(CalendarView.this);

						// set some properties of rowTextView or something
						rowTextView.setText("Event:" + desc.get(i));
						rowTextView.setTextColor(Color.BLACK);

						// add the textview to the linearlayout
						rLayout.addView(rowTextView);

					}

				}

				desc = null;

			}
        
		});
		
		
		
		current_year_current.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				cs=db.query("MY_EXP",new String[]{"Amount"},"Date LIKE ?", new String[]{y_year+"-%"},null,null,null);
				if(cs.moveToFirst())
				{
					do
					{
						Log.d("aaa","ggffh");
						int amount_year=Integer.parseInt(cs
								.getString(0));
						sum_value_year=sum_value_year+amount_year;
						summm_year=""+sum_value_year;
						current_year_tv.setText(summm_year);
						Log.d("%%%%",""+sum_value);
						
					
					}while(cs.moveToNext());
				}
					
				sum_value_year=0;	
					
			}
		});
	current_month.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			// TODO Auto-generated method stub
		
			cs1=db.query("MY_EXP",new String[]{"Amount"},"Date LIKE ?", new String[]{y_year+"-"+m_month+"-%"},null,null,null);
			if(cs1.moveToFirst())
			{
				do
				{
					Log.d("aaa","ggffh");
					int amount=Integer.parseInt(cs1.getString(0));
					sum_value=sum_value+amount;
					summm=""+sum_value;
					current_month_tv.setText(summm);
					Log.d("%%%%",""+sum_value);
					
				
				}while(cs1.moveToNext());
			}
				
			sum_value=0;	
			
			
			
			
		}
	});	
/*	current_year.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			// TODO Auto-generated method stub

			// TODO Auto-generated method stub
		
			cs2=db.query("MY_EXP",new String[]{"Amount"},"Date LIKE ?", new String[]{y_year+"-"+m_month+"-%"},null,null,null);
			if(cs2.moveToFirst())
			{
				do
				{
					Log.d("aaa","ggffh");
					int amount=Integer.parseInt(cs2.getString(0));
					sum_value=sum_value+amount;
					summm=""+sum_value;
					current_year_tv.setText(summm);
					Log.d("%%%%",""+sum_value);
					
				
				}while(cs2.moveToNext());
			}
				
			sum_value=0;	
			
			
			
			
			
		}
	});
		
	*/	
		
		
		
		
		
	}

	protected void setNextMonth() {
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMaximum(GregorianCalendar.MONTH)) {
			month.set((month.get(GregorianCalendar.YEAR) + 1),
					month.getActualMinimum(GregorianCalendar.MONTH), 1);
		} else {
			month.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) + 1);
		}

	}

	protected void setPreviousMonth() {
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMinimum(GregorianCalendar.MONTH)) {
			month.set((month.get(GregorianCalendar.YEAR) - 1),
					month.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			month.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) - 1);
		}

	}

	protected void showToast(String string) {
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();

	}

	public void refreshCalendar() {
		TextView title = (TextView) findViewById(R.id.title);

		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		handler.post(calendarUpdater); // generate some calendar items

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
	}

	public Runnable calendarUpdater = new Runnable() {

		@Override
		public void run() {
			items.clear();

			// Print dates of the current week
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
			String itemvalue;
			event = Utility.readCalendarEvent(CalendarView.this);
			Log.d("=====Event====", event.toString());
			Log.d("=====Date ARRAY====", Utility.startDates.toString());

			for (int i = 0; i < Utility.startDates.size(); i++) {
				itemvalue = df.format(itemmonth.getTime());
				itemmonth.add(GregorianCalendar.DATE, 1);
				items.add(Utility.startDates.get(i).toString());
			}
			adapter.setItems(items);
			adapter.notifyDataSetChanged();
		}
	};
}

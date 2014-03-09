package com.example.expenditure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView tv;
	SQLiteDatabase db;
	DbHelper_exp dbhelper_exp;
	EditText ed_item;
	EditText ed_amount;
	Cursor cs1,cs2;
	TableRow t_row;
	TextView year_month_day;
	int sum_value=0;
	TextView sum_value_tv;
	TableLayout table;
	CheckBox cb;
	String last_items;
	String last_amount;
	Boolean value=false;
	String summm;
	Button total_exp;
	static String items;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_activity_main);
		Intent in=getIntent();
		Bundle b=in.getExtras();
		final String current_date=b.getString("date");
		String current_year=current_date.substring(0,4);
		//Log.d("aaabbbb??",current_date);
		
		
		
		//Log.d("????",current_year);
		year_month_day=(TextView)findViewById(R.id.textView_month);
		year_month_day.setText(current_date);
		total_exp=(Button)findViewById(R.id.button_total_exp);
		
		
		sum_value_tv=(TextView)findViewById(R.id.textView_sumvalue);
		sum_value_tv.setText(summm);
		
		dbhelper_exp=new DbHelper_exp(this, "My_Exp", null, 1);
		db=dbhelper_exp.getWritableDatabase();
		
		total_exp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				cs2=db.query("MY_EXP",new String[]{"Amount"},"Date=?",new String[]{current_date},null,null,null);
				if(cs2.moveToFirst())
				{
					do
					{
						//Log.d("aaa","ggffh");
						int amount=Integer.parseInt(cs2.getString(0));
						sum_value=sum_value+amount;
						summm=""+sum_value;
						sum_value_tv.setText(summm);
						
					
					}while(cs2.moveToNext());
				}
					
				sum_value=0;	
				
				
			}
		});
		
		
		
			
		//sum_value_tv.setText(summm);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		cs1=db.query("MY_EXP",new String[]{"Date","Items","Amount"},"Date=?",new String[]{current_date},null,null,null);
				if(cs1.moveToFirst()){
					do{
						

						TableLayout table=(TableLayout)findViewById(R.id.table);
						TableRow t_row=new TableRow(getApplicationContext());
						//int row_id =t_row.getId();
						t_row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
						ed_item=new EditText(getApplicationContext());
						ed_item.setWidth(160);
						cb=new CheckBox(getApplicationContext());
					
						ed_amount=new EditText(getApplicationContext());
						ed_amount.setWidth(100);
						t_row.addView(ed_item);
						t_row.addView(ed_amount);
						t_row.addView(cb);
						table.addView(t_row, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
						String month=cs1.getString(0);
						String items=cs1.getString(1);
						
						String amount=cs1.getString(2);
						year_month_day.setText(current_date);
						 ed_item.setText(items);
				 ed_amount.setText(amount);
						
				}while(cs1.moveToNext());
				}
					
		Button add_row=(Button)findViewById(R.id.add_row);
		add_row.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TableLayout table=(TableLayout)findViewById(R.id.table);
				TableRow t_row=new TableRow(getApplicationContext());
				//int row_id =t_row.getId();
				t_row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				ed_item=new EditText(getApplicationContext());
				cb=new CheckBox(getApplicationContext());
				
				ed_item.setWidth(100);
			
				ed_amount=new EditText(getApplicationContext());
				ed_amount.setWidth(100);
				t_row.addView(ed_item);
				t_row.addView(ed_amount);
				t_row.addView(cb);
				table.addView(t_row, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				value=true;

	}
		});
		
		
		
		
	
	
		
		
	
			
		
			
			
		
	
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			
		
			
			
		Button save=(Button)findViewById(R.id.save);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				
				if(value==true)
				{
				
				
			
				if((ed_item.getText().toString().length()>0)&&(ed_amount.getText().toString().length()>0))
				{
					  ContentValues values=new ContentValues(); 
					  values.put("Date", current_date);
					  values.put("Items", ed_item.getText().toString()); 
					  values.put("Amount", ed_amount.getText().toString()); 
					long result= db.insert("MY_EXP", "my_exp", values); 
					
					if(result==-1)
					{
						Toast.makeText(MainActivity.this, "Data was not inserted", Toast.LENGTH_SHORT).show();

					}
					else
					{
						Toast.makeText(MainActivity.this, "Data was sucessfully inserted", Toast.LENGTH_SHORT).show();
							
							
					}
					  
				}
				else
				{
					Toast.makeText(MainActivity.this, "Fill in the Details", Toast.LENGTH_SHORT).show();
				}
				
				}
				
				
				
				
				
				
		//		cs2=db.query("MY_EXP",new String[]{"Amount"},"Month=?",new String[]{"January"},null,null,null);
			//	if(cs2.moveToFirst())
				//{
					//do
				//	{
				//		Log.d("aaa","ggffh");
			//			int amount=Integer.parseInt(cs2.getString(0));
				//		sum_value=sum_value+amount;
					//	String summm=""+sum_value;
//						sum_value_tv.setText(summm);
	//				}while(cs2.moveToNext());
		//		}
					
			//	sum_value=0;	
					
				
				
				
				
				
				
				
				
				
				
				
				
				
				}	}
		
		);
		
		Button delete=(Button)findViewById(R.id.delete);
		delete.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				TableLayout table=(TableLayout)findViewById(R.id.table);
				for(int i=0;i<table.getChildCount();i++)
				{
					TableRow t_row=(TableRow)table.getChildAt(i);
					CheckBox cbb=(CheckBox) t_row.getChildAt(2);
				
						if(cbb.isChecked())
						{
							t_row.removeViewAt(i);
						//	int result= db.delete("MY_EXP", "Items=?",  new String[]{ed_item.getText().toString()}); 

							//  if(result==0)
							//	{
							//		Toast.makeText(MainActivity.this, "The requested record does not exist", Toast.LENGTH_SHORT).show();

							//	}
							//	else
								//{
								//	Toast.makeText(MainActivity.this, "Data was sucessfully Deleted", Toast.LENGTH_SHORT).show();
										
								//	ed_item.setText("");
								//	ed_amount.setText("");
								//}

						}
					}
				}
				
				
				
			
			/*	
				
				
				
				
				
				
				
				
				
				// TODO Auto-generated method stub
			
				if(ed_item.getText().toString().length()>0)
				{
					  int result= db.delete("MY_EXP", "Items=?",  new String[]{ed_item.getText().toString()}); 

					  if(result==0)
						{
							Toast.makeText(MainActivity.this, "The requested record does not exist", Toast.LENGTH_SHORT).show();

						}
						else
						{
							Toast.makeText(MainActivity.this, "Data was sucessfully Deleted", Toast.LENGTH_SHORT).show();
								
							ed_item.setText("");
							ed_amount.setText("");
						}
				}
				else
				{
					Toast.makeText(MainActivity.this, "Fill in the Details", Toast.LENGTH_SHORT).show();
				}
				
				
				*/
				
				
			
		});
		
}
		
		
		
	}	
		
	

	



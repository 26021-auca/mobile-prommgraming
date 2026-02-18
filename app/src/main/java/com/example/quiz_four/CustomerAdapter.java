package com.example.quiz_four;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomerAdapter extends ArrayAdapter<Customer> {

    private Context context;
    private List<Customer> customerList;
    private DatabaseHelper dbHelper;

    public CustomerAdapter(Context context, List<Customer> customerList, DatabaseHelper dbHelper) {
        super(context, 0, customerList);
        this.context = context;
        this.customerList = customerList;
        this.dbHelper = dbHelper;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.customer_item, parent, false);

        final Customer currentCustomer = customerList.get(position);

        TextView tvNames = listItem.findViewById(R.id.tv_names);
        tvNames.setText(currentCustomer.getFullNames());

        TextView tvNid = listItem.findViewById(R.id.tv_nid);
        tvNid.setText("NID/Passport: " + currentCustomer.getCustomerNID());

        TextView tvAgeGender = listItem.findViewById(R.id.tv_age_gender);
        int age = getAge(currentCustomer.getBirthDate());
        tvAgeGender.setText("Age: " + age + ", Gender: " + currentCustomer.getGender());

        Button btnEdit = listItem.findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CustomerActivity.class);
                intent.putExtra("customer_id", currentCustomer.getCustomerID());
                context.startActivity(intent);
            }
        });

        Button btnDelete = listItem.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteCustomer(currentCustomer.getCustomerID());
                customerList.remove(currentCustomer);
                notifyDataSetChanged();
            }
        });

        return listItem;
    }

    private int getAge(Date dateOfBirth) {
        Calendar dob = Calendar.getInstance();
        dob.setTime(dateOfBirth);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }
}

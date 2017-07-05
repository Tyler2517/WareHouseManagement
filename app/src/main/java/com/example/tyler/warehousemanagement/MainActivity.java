
        package com.example.tyler.warehousemanagement;

        import android.app.DialogFragment;
        import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.design.widget.TabLayout;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;

        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Collection;
        import java.util.Iterator;
        import java.util.List;
        import java.util.ListIterator;

        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;
        import android.widget.Adapter;

        import static com.example.tyler.warehousemanagement.R.layout.tab1;

        /**************************************
 *
 *  MAIN ACTIVITY
 *
 **************************************/
public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    List<Item> Inventory;
    List<Item> NameSort;
    List<Item> IDSort;
    List<Item> ConSort;
    List<Item> LocSort;
    String [] data = {""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the tabs
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        //read in data
        try {
            data[0] = read(MainActivity.this, "Data.txt");
            //testing only
            String normal = data[0];
            Toast.makeText(getApplicationContext(), normal, Toast.LENGTH_LONG).show();
            Inventory = JSONConv(normal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //List view fun
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, tab1, data);
        ListView lv = (ListView)findViewById(R.id.ListViewTab1);
        lv.setAdapter(adapter);

        //floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment = AddEditDialog.newInstance(view.getId());
                newFragment.setShowsDialog(true);
                newFragment.show(getFragmentManager(), "dialog");
            }
        });


    }

    /**************************************
     *
     *  onCreateOptionsMenu
     *
     **************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    /**************************************
     *
     *  onOptionsItemSelected
     *
     **************************************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**************************************
     *
     *  ReadFile
     *
     **************************************/
    public String read(Context context, String File) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(File)));
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append(mLine);
            mLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }


    /**************************************
     *
     *  SectionsPagerAdapter
     *
     **************************************/

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            switch(position){
                case 0:
                    Tab1 tab1 = new Tab1();
                    return tab1;

                case 1:
                    Tab2 tab2 = new Tab2();
                    return tab2;
                case 2:
                    Tab3 tab3 = new Tab3();
                    return tab3;
                case 3:
                    Tab4 tab4 = new Tab4();
                    return tab4;
                case 4:
                    Tab5 tab5 = new Tab5();
                    return tab5;
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            // Show 5 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "Sort1";
                case 2:
                    return "Sort2";
                case 3:
                    return "Sort3";
                case 4:
                    return "Sort4";
            }
            return null;
        }
    }

    /**************************************
     *
     *  JSON converter
     *
     **************************************/
    public List<Item> JSONConv(String item){
        List<Item> Inventory= new List<Item>() {
            @Override
            public int size() {
                return 0;
            }
            @Override
            public boolean isEmpty() {
                return false;
            }
            @Override
            public boolean contains(Object o) {
                return false;
            }
            @NonNull
            @Override
            public Iterator<Item> iterator() {
                return null;
            }
            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }
            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] a) {
                return null;
            }
            @Override
            public boolean add(Item item) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }
            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }
            @Override
            public boolean addAll(@NonNull Collection<? extends Item> c) {
                return false;
            }
            @Override
            public boolean addAll(int index, @NonNull Collection<? extends Item> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Item get(int index) {
                return null;
            }

            @Override
            public Item set(int index, Item element) {
                return null;
            }

            @Override
            public void add(int index, Item element) {

            }

            @Override
            public Item remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Item> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Item> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<Item> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        try {
            JSONObject object = new JSONObject(item);
            JSONArray inventory = object.getJSONArray("Warehouse");
            for (int i = 0; i < inventory.length()-1; i++) {
                Item temp = new Item();
                JSONObject jsonInventory = inventory.getJSONObject(i);
                temp.Name = jsonInventory.getString("Name");
                temp.ID = jsonInventory.getString("ID");
                temp.Condition = jsonInventory.getString("Condition");
                temp.Location = jsonInventory.getString("Location");
                Inventory.add(i,temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Failure to load JSON", Toast.LENGTH_SHORT).show();
        }

        return Inventory;
    }
}
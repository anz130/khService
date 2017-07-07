package com.dyb.dybc.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.dyb.dybc.R;
import com.dyb.dybc.adapter.CityPickerListAdapter;
import com.dyb.dybc.adapter.CityPickerResultListAdapter;
import com.dyb.dybc.bean.City;
import com.dyb.dybc.bean.LocateState;
import com.dyb.dybc.config.Config;
import com.dyb.dybc.listener.LocationListener;
import com.dyb.dybc.util.LocalClient;
import com.dyb.dybc.util.StringUtils;
import com.dyb.dybc.views.SideLetterBar;
import com.dyb.dybc.views.TitleModule;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.zhy.zhylib.base.BaseActivity;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * zhangyong
 */
public class CityPickerActivity extends BaseActivity implements View.OnClickListener,EasyPermissions.PermissionCallbacks{
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search_clear)
    ImageView ivSearchClear;
    @BindView(R.id.listview_all_city)
    ListView listviewAllCity;
    @BindView(R.id.tv_letter_overlay)
    TextView tvLetterOverlay;
    @BindView(R.id.side_letter_bar)
    SideLetterBar sideLetterBar;
    @BindView(R.id.listview_search_result)
    ListView listviewSearchResult;
    @BindView(R.id.empty_view)
    LinearLayout emptyView;

    private CityPickerListAdapter mCityAdapter;
    private CityPickerResultListAdapter mResultAdapter;
    private List<City> mAllCities;
    private List<City> resultCities;

    private HanyuPinyinOutputFormat format;

    private LocalClient localClient;

    private TitleModule titleModule;

    String[] locationPermissions = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_picker);
        ButterKnife.bind(this);
        init();
        setListener();
    }

    private void init() {
        localClient = new LocalClient(mActivity);
        titleModule = new TitleModule(this,"搜索城市");
        format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        mAllCities = new ArrayList<>();
        resultCities = new ArrayList<>();
        mAllCities = getAllCities();

        mResultAdapter = new CityPickerResultListAdapter(this, null);
        mCityAdapter = new CityPickerListAdapter(this, mAllCities);

        listviewAllCity.setAdapter(mCityAdapter);
        sideLetterBar.setOverlay(tvLetterOverlay);
        listviewSearchResult.setAdapter(mResultAdapter);
    }

    private void setListener() {
        mCityAdapter.setOnCityClickListener(new CityPickerListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String name) {
                back(name);
            }

            @Override
            public void onLocateClick() {
                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                if (EasyPermissions.hasPermissions(mContext, locationPermissions)) {
                    localClient.startLocation();
                } else {
                    EasyPermissions.requestPermissions(this, "需要定位权限", Config.LOCATION, locationPermissions);
                }

            }
        });

        sideLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                listviewAllCity.setSelection(position);
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
                    ivSearchClear.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    listviewSearchResult.setVisibility(View.GONE);
                } else {
                    ivSearchClear.setVisibility(View.VISIBLE);
                    listviewSearchResult.setVisibility(View.VISIBLE);

                    resultCities = getSearchList(keyword);
                    if (resultCities == null || resultCities.size() == 0) {
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        emptyView.setVisibility(View.GONE);
                        mResultAdapter.changeData(resultCities);
                    }
                }
            }
        });


        listviewSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                back(resultCities.get(position).getName());
            }
        });

        localClient.setLocationListener(new LocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                if (bdLocation != null) {
                    if (bdLocation.getLocType() != BDLocation.TypeServerError) {
                        String city = bdLocation.getCity();
                        String district = bdLocation.getDistrict();
                        String location = StringUtils.extractLocation(city, district);
                        mCityAdapter.updateLocateState(LocateState.SUCCESS, location);
                    } else {
                        mCityAdapter.updateLocateState(LocateState.FAILED, null);
                    }
                }
                localClient.stopLocation();
            }
        });


        if (EasyPermissions.hasPermissions(this, locationPermissions)) {
            localClient.startLocation();
        } else {
            EasyPermissions.requestPermissions(this, "需要定位权限", Config.LOCATION, locationPermissions);
        }

        ivSearchClear.setOnClickListener(this);
    }

    private void back(String city) {
        Intent data = new Intent();
        data.putExtra("city", city);
        setResult(Config.SEARCH_CITY, data);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search_clear:
                etSearch.setText("");
                ivSearchClear.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                listviewSearchResult.setVisibility(View.GONE);
                break;
        }
    }

    private List<City> getSearchList(String keyword){
        List<City> list = new ArrayList<>();
        for(City city : mAllCities){
            if(city.getName().contains(keyword)){
                list.add(city);
            }
        }
        return list;
    }

    private List<City> getAllCities() {
        try {
            String strByJson = getFromAssets();
            JsonObject jsonObject = new JsonParser().parse(strByJson).getAsJsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray("City");
            Gson gson = new Gson();
            ArrayList<City> cities = new ArrayList<>();
            for (JsonElement user : jsonArray) {
                City city = gson.fromJson(user, new TypeToken<City>() {}.getType());
                city.setPinyin(PinyinHelper.toHanyuPinyinStringArray(city.getName().toCharArray()[0], format)[0]);
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFromAssets() {
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open("allcity.json"));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localClient.destoryLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        localClient.startLocation();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        showToast("获取定位权限失败");
    }
}

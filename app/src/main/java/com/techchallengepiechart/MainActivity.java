package com.techchallengepiechart;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.techchallengepiechart.databinding.ActivityMainBinding;
import com.techchallengepiechart.model.MCourse;
import com.techchallengepiechart.utils.Range;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MCourse mCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init() {
        String data = loadJSONFromAsset();
        mCourse = new Gson().fromJson(data, MCourse.class);
        binding.tvCourseName.setText(mCourse.getCourseName());
        if (mCourse.getAbilities().size() > 0) {
            binding.tvAbilityOne.setText(mCourse.getAbilities().get(0).getName());
            binding.tvAbilityOneValue.setText(getPercentage(mCourse.getAbilities().get(0).getTotalMarksObtained(), mCourse.getAbilities().get(0).getOutOf()) + "%");

            binding.tvAbilityThree.setText(mCourse.getAbilities().get(1).getName());
            binding.tvAbilityThreeValue.setText(getPercentage(mCourse.getAbilities().get(1).getTotalMarksObtained(), mCourse.getAbilities().get(1).getOutOf()) + "%");

            binding.tvAbilityTwo.setText(mCourse.getAbilities().get(2).getName());
            binding.tvAbilityTwoValue.setText(getPercentage(mCourse.getAbilities().get(2).getTotalMarksObtained(), mCourse.getAbilities().get(2).getOutOf()) + "%");

            binding.tvAbilityFour.setText(mCourse.getAbilities().get(3).getName());
            binding.tvAbilityFourValue.setText(getPercentage(mCourse.getAbilities().get(3).getTotalMarksObtained(), mCourse.getAbilities().get(3).getOutOf()) + "%");
            setChart();
        }
    }

    private void setChart() {

        Range range = new Range();
        range.setColor(getResources().getColor(R.color.yellowColor));
        range.setFrom(0.0);
        range.setTo(getFloatPercentage(mCourse.getAbilities().get(0).getTotalMarksObtained(), mCourse.getAbilities().get(0).getOutOf()));

        Range range2 = new Range();
        range2.setColor(getResources().getColor(R.color.purpleColor));
        range2.setFrom(0.0);
        range2.setTo(getFloatPercentage(mCourse.getAbilities().get(1).getTotalMarksObtained(), mCourse.getAbilities().get(1).getOutOf()));

        Range range3 = new Range();
        range3.setColor(getResources().getColor(R.color.pinkColor));
        range3.setFrom(0.0);
        range3.setTo(getFloatPercentage(mCourse.getAbilities().get(2).getTotalMarksObtained(), mCourse.getAbilities().get(2).getOutOf()));

        Range range4 = new Range();
        range4.setColor(getResources().getColor(R.color.tealColor));
        range4.setFrom(0.0);
        range4.setTo(getFloatPercentage(mCourse.getAbilities().get(3).getTotalMarksObtained(), mCourse.getAbilities().get(3).getOutOf()));

        binding.chart.setMinValue(0.0);
        binding.chart.setMaxValue(mCourse.getAbilities().get(0).getOutOf());
        binding.chart.setValue(mCourse.getAbilities().get(0).getTotalMarksObtained());

        binding.chart.setSecondMinValue(0.0);
        binding.chart.setSecondMaxValue(mCourse.getAbilities().get(1).getOutOf());
        binding.chart.setSecondValue(mCourse.getAbilities().get(1).getTotalMarksObtained());

        binding.chart.setThirdMinValue(0.0);
        binding.chart.setThirdMaxValue(mCourse.getAbilities().get(2).getOutOf());
        binding.chart.setThirdValue(mCourse.getAbilities().get(2).getTotalMarksObtained());

        binding.chart.setFourthMinValue(0.0);
        binding.chart.setFourthMaxValue(mCourse.getAbilities().get(3).getOutOf());
        binding.chart.setFourthValue(mCourse.getAbilities().get(3).getTotalMarksObtained());

        binding.chart.setDisplayValuePoint(true);
        binding.chart.setUseRangeBGColor(true);

        binding.chart.addRange(range);
        binding.chart.addSecondRange(range2);
        binding.chart.addThirdRange(range3);
        binding.chart.addFourthRange(range4);
    }

    public Float getFloatPercentage(float marksObtained, float outOf) {
        return ((marksObtained / outOf) * 100);
    }

    public String getPercentage(float marksObtained, float outOf) {
        return String.format("%.2f", getFloatPercentage(marksObtained, outOf));
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("science.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
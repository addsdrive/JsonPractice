
package com.shafqat.jsonpractice.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("1st-year")
    @Expose
    private List<ClassYear> _1stYear = null;
    @SerializedName("2nd-Year-A")
    @Expose
    private List<ClassYear> _2ndYear = null;

    public List<ClassYear> get1stYear() {
        return _1stYear;
    }

    public void set1stYear(List<ClassYear> _1stYear) {
        this._1stYear = _1stYear;
    }

    public List<ClassYear> get2ndYear() {
        return _2ndYear;
    }

    public void set2ndYear(List<ClassYear> _2ndYear) {
        this._2ndYear = _2ndYear;
    }

}

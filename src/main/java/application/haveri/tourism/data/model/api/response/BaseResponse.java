package application.haveri.tourism.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BaseResponse extends ApiError {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("states")
    @Expose
    private List<State> states = null;

    public Boolean getSuccess() {
        if(success == null) {
            success = false;
        }
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<State> getStates() {
        if(states == null) {
            return new ArrayList<>();
        }
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}

package fitpay.engtest.model;

import java.util.List;

public class Devices {

    private List<Device> results;

    public List<Device> getResults() {
        return results;
    }

    public void setResult(List<Device> results) {
        this.results = results;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Device d: results){
            s.append(d.toString() + ",\n");
        }

        return s.toString();
    }


}

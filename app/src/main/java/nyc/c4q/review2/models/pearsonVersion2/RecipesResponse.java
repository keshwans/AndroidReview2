
package nyc.c4q.review2.models.pearsonVersion2;

import java.util.ArrayList;
import java.util.List;

public class RecipesResponse {

    private String kind;
    private String url;
    private Integer count;
    private Integer offset;
    private Integer limit;
    private Integer total;

    private List<Result> results = new ArrayList<>();

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "RecipesResponse{" + "\n" +
                "kind='" + kind + "'\n" +
                ", count=" + count +
                ", offset=" + offset +
                ", limit=" + limit +
                ", total=" + total +'\n' +
                ", url='" + url + "'\n" +
                ", results=" + results.get(0) +'\n' +
                '}';
    }

}

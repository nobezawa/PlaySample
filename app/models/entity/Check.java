package models.entity;

import play.data.format.Formats;
import play.data.validation.*;
import play.db.ebean.Model;
import play.libs.F;


import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * Created by nobesawa on 15/06/13.
 */

@Entity

@Table(name = "checks")
public class Check extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String result;

    @Formats.DateTime(pattern = "yyyy/MM/dd")
    public Date created;

    @Formats.DateTime(pattern = "yyyy/MM/dd")
    public Date modified;


    public Check() {super();}

    public Check(String name) {
        this.name = name;
    }

    public Check(Long id) {
        this.id = id;
    }

    public Check(String name, String result) {
        this.name = name;
        this.result = result;
        this.created = new Date();
        this.modified = new Date();
    }

    @Transient
    private CheckService checkService = new CheckService();

    @Transient
    private CheckModelService checkModelService = new CheckModelService();


    /**
     * 結果を取得
     * @return
     */
    public F.Option<String> result() {
        return checkService.getResult(name);
    }

    /**
     * 結果を保存
     * @return
     */
    public F.Option<Check> store() {
        return checkModelService.save(this);
    }

    /**
     * idに該当するものを検索
     * @return
     */
    public F.Option<Check> unique() {
        return checkModelService.findById(id);
    }

    /**
     * 指定ページの一覧
     * @param page
     * @return
     */
    public F.Option<List<Check>> entitiesList(Integer page) {
        return checkModelService.findWithPage(page);
    }

    /**
     * ページ結果を取得
     * @param
     * @return
     */
    public Integer entitiesMaxPage(Integer value) {
        return checkModelService.getMaxPage().getOrElse(value);
    }


}

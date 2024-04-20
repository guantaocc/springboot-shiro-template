package com.rain.shiro.project.templates;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GenerateReport {
    /**
     * 根据数据生成 word文档
     * @param data
     */
    public String generateSubscribeReport(Map data, String relationPath) throws Exception{
        System.out.println(data.toString());

        Map<String, Object> datas = new HashMap<String, Object>();

        System.out.println(data.get("insititutionInfo"));

        List<HashMap<String, String>> listData = (List<HashMap<String, String>>) data.get("checkedList");

        // create table
        RowRenderData header = Rows.of("体检项", "体检结果")
                .textColor("FFFFFF")
                .bgColor("ff9800")
                .center()
                .rowHeight(2.5f)
                .create();


        List<RowRenderData> rowDataList = new ArrayList<>();

        rowDataList.add(header);

        for (HashMap<String, String> obj : listData){
            String name = obj.get("title");
            String isNormal = obj.get("isNormal");
            RowRenderData row = Rows.create(name, isNormal);
            rowDataList.add(row);
        }

        TableRenderData table = Tables.create(rowDataList.toArray(new RowRenderData[0]));

        HashMap package1 = (HashMap) data.get("packageInfo");
        String packageName = (String) package1.get("name");
        String price = (String) package1.get("price");

        HashMap institution = (HashMap) data.get("insititutionInfo");
        String institutionName = (String) institution.get("name");
        String address = (String) institution.get("address");
        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();

        // 创建日期时间格式化器，定义想要的日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化当前时间
        String formattedTime = currentTime.format(formatter);

        // text
        datas.put("name", "机构");
        datas.put("institution", Texts.of(institutionName).color("000000").create());
        datas.put("package", Texts.of(packageName).color("ff9800").create());
        datas.put("price", Texts.of(price).color("ff9800").create());
        datas.put("create_time", Texts.of(formattedTime).color("000000").create());
        datas.put("address", Texts.of(address).color("000000").create());
        // table
        datas.put("solution_compare", table);

        String projectPath = System.getProperty("user.dir");
        String templatePath = projectPath + "\\"  + "src\\main\\resources\\template\\template.docx";
        String uuid = String.valueOf(UUID.randomUUID());
        String returnPath = "/" + uuid + "_" + "template.docx";
        String targetPath = projectPath + "\\"  + "file\\" + uuid + "_" + "template.docx";

        XWPFTemplate.compile(templatePath)
                .render(datas)
                .writeToFile(targetPath);
        return returnPath;
    }
}

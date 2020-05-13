package com.wmx.thymeleafapp.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.logging.Logger;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/13 9:40
 */
public class FileWmxUtils {

    private static Logger logger = Logger.getAnonymousLogger();

    /**
     * 1、生成指定目录下的所有文件与目录的树级 json 字符串.
     * 2、格式：[{"name":"父节点1-wmx - 展开","open":true,"children":[{"name":"父节点11 - 折叠","children":[{"name":"叶子节点111"},{"name":"叶子节点112"}]},{"name":"父节点12 - 折叠","children":[{"name":"叶子节点121"},{"name":"叶子节点122"}]},{"name":"父节点13 - 没有子节点","isParent":true}]},{"name":"父节点2 - 折叠","children":[{"name":"父节点21 - 展开","open":true,"children":[{"name":"叶子节点211"},{"name":"叶子节点212"},{"name":"叶子节点213"},{"name":"叶子节点214"}]}]
     *
     * @param rootFile ：待生成的树的目录.
     * @param count    ：默认打开的多少个目录
     * @return : 返回约定格式的 json 数组，即一个目录下有哪些子文件或子文件夹.
     */
    public static JsonArray fileTreeByDirPath(File rootFile, int count) {
        JsonArray jsonArray = new JsonArray();
        File[] fileArr = rootFile.listFiles();

        if (!rootFile.exists() || !rootFile.isDirectory()) {
            logger.warning(rootFile + " 目录不存在...");
            return jsonArray;
        }
        for (File file : fileArr) {
            if (file.isFile()) {
                /**如果当前是文件*/
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", file.getName());
                jsonArray.add(jsonObject);
            } else {
                /**如果当前是目录*/
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", file.getName());
                if (count > 0) {
                    jsonObject.addProperty("open", true);
                }
                JsonArray childrenJsonArray = fileTreeByDirPath(file, --count);
                jsonObject.add("children", childrenJsonArray);
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
    }
}
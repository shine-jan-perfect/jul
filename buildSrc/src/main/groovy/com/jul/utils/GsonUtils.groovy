package com.jul.utils


import com.google.gson.GsonBuilder

class GsonUtils {
    static def readJson(def filePath, def clz) {
        File file = new File(filePath)

        Reader reader = null
        try {
            reader = new FileReader(file)

            def gsonBuilder = new GsonBuilder()
            gsonBuilder.setPrettyPrinting()
            def gson = gsonBuilder.create()
            gson.fromJson(reader, clz)
        } catch (IOException e) {
            e.printStackTrace()
        } finally {
            if (reader != null) {
                reader.close()
            }
        }
    }

    static def writeJson(def filePath, def obj, boolean append) {
        File file = new File(filePath)

        def writer = null
        try {
            writer = new FileWriter(file, append)

            def gsonBuilder = new GsonBuilder()
            gsonBuilder.setPrettyPrinting()
            def gson = gsonBuilder.create()
            gson.toJson(obj, writer)
        } catch (IOException e) {
            e.printStackTrace()
        } finally {
            if (writer != null) {
                writer.close()
            }
        }
    }
}
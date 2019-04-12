package com.jul.utils

class FileUtils {
    static def getCurDir() {
        def directory = new File("") //设定为当前文件夹
        try {
            println directory.getCanonicalPath()
            println directory.getAbsolutePath()
            println directory.getPath()
        } catch (IOException e) {
            e.printStackTrace()
        }
    }

    static def readFile(def filePath) {
        File file = new File(filePath)

        BufferedReader bufferedReader = null
        try {
            bufferedReader = new BufferedReader(new FileReader(file))
            String str = null

            def stringBuilder = new StringBuilder()
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str)
            }

            stringBuilder.toString()
        } catch (IOException e) {
            e.printStackTrace()
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close()
            }
        }
    }

    static def writeFile(def filePath, def str, boolean append) {
        File file = new File(filePath)

        BufferedWriter bufferedWriter = null
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, append))
            bufferedWriter.write(str)
        } catch (IOException e) {
            e.printStackTrace()
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close()
            }
        }
    }
}
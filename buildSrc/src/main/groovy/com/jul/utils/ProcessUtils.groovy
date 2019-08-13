package com.jul.utils

class ProcessUtils {
    static def ENCODING_UTF8 = 'utf-8'
    static def ENCODING_GBK = 'gbk'

    static def exec(def command, def encoding = ENCODING_UTF8) {
        println "command -> $command"

        def out = new ByteArrayOutputStream()
        def err = new ByteArrayOutputStream()

        def process = command.execute()
        process.consumeProcessOutput(out, err)
        process.waitFor()

        println "process code: ${process.exitValue()}"
        println "process err: ${err.toString(encoding)}"
        println "process out: ${out.toString(encoding)}"
    }

    static def execPip(def command, def encoding = ENCODING_UTF8) {
        println "os -> ${OsUtils.getOS()}"

        println "command -> $command"

        String[] batchCommand = new String[2]
        if (OsUtils.isWindows()) {
            batchCommand[0] = 'cmd'
            batchCommand[1] = '/c'
        } else {
            batchCommand[0] = '/bin/bash'
            batchCommand[1] = '-c'
        }

        def out = new ByteArrayOutputStream()
        def err = new ByteArrayOutputStream()
        def process = [batchCommand[0], batchCommand[1], command].execute()
        process.consumeProcessOutput(out, err)
        process.waitFor()

        println "process code: ${process.exitValue()}"
        println "process err: ${err.toString(encoding)}"
        println "process out: ${out.toString(encoding)}"

        //return out string
        out.toString(encoding)
    }
}
package com.example.gamebacklog.data

 class StringConverters {

    /**
     * To make sure that words are
     */
     fun beautifyString(string: String) : String {

        val loweredCase : String = string.toLowerCase()
//        val result : String = loweredCase.capitalize()
//
//        return result

        val words = loweredCase.split(" ").toMutableList()

        var output = ""

        for(word in words){
            output += word.capitalize() + " "
        }

        output = output.trim()

        return output
    }
}
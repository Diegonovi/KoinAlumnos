package org.example

import org.koin.core.context.startKoin
import org.koin.fileProperties

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    startKoin{
        fileProperties("/config.properties")
        modules()
    }
}
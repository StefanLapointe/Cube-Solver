package com.stefanlapointe;

public enum Sticker {

    BLUE('B'),
    GREEN('G'),
    ORANGE('O'),
    RED('R'),
    WHITE('W'),
    YELLOW('Y');

    final char initial;

    Sticker(char initial) {
        this.initial = initial;
    }

}

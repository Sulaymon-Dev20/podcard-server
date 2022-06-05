package com.example.podcastserver.util;

public class UzLanguageConverter {
    public static String ltToKr(String text) {
        return text.replace("YO", "Ё").replace("CH", "Ч").replace("SH", "Ш").replace("YU", "Ю").replace("YA", "Я").replace("O`", "Ў").replace("O'", "Ў").replace("G`", "Ғ").replace("G'", "Ғ")
                .replace('A', 'А').replace('B', 'Б').replace('V', 'В').replace('G', 'Г').replace('D', 'Д')
                .replace('E', 'Е').replace('J', 'Ж').replace('Z', 'З').replace('I', 'И')
                .replace('Y', 'Й').replace('K', 'К').replace('L', 'Л').replace('M', 'М').replace('N', 'Н')
                .replace('O', 'О').replace('P', 'П').replace('R', 'Р').replace('S', 'С').replace('T', 'Т')
                .replace('U', 'У').replace('F', 'Ф').replace('X', 'Х').replace('S', 'Ц').replace('E', 'Э').replace('Q', 'Қ').replace('X', 'Ҳ');
    }
}

package application.haveri.tourism.utils;

import android.util.SparseArray;

public enum Language {
    EN(0, "en"), KN(1, "kn");
    private int value;
    private String name;

    private static SparseArray<Language> sparseArray = new SparseArray<>();

    static {
        for (Language lang : Language.values()) {
            //map.put(lang.value, lang);
            sparseArray.put(lang.value,lang);
        }
    }

    Language(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Language valueOf(int langIndex) {
        if (langIndex <= sparseArray.size()) {
            return sparseArray.get(langIndex);
        } else {
            throw new IllegalArgumentException("Out of index " + langIndex + " - Size -" + sparseArray.size());
        }
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
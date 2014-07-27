package com.emmaguy.todayilearned.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Listing {
    private List<TIL> mTILs = new ArrayList<TIL>();
    public String before;

    public void addTIL(TIL til) {
        mTILs.add(til);
    }

    public Iterable<? extends TIL> getTodayILearneds() {
        return mTILs;
    }

    public static class ListingJsonDeserializer implements JsonDeserializer<Listing> {
        @Override
        public Listing deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Listing l = new Listing();
            JsonObject dataObject = json.getAsJsonObject().get("data").getAsJsonObject();
            for (JsonElement e : dataObject.get("children").getAsJsonArray()) {
                JsonObject data = e.getAsJsonObject().get("data").getAsJsonObject();
                boolean stickied = data.get("stickied").getAsBoolean();
                if (!stickied) {
                    l.addTIL(new TIL(data.get("title").getAsString(), data.get("id").getAsString()));
                }
            }

            if (l.mTILs.size() > 0) {
                l.before = "t3_" + l.mTILs.get(0).getId();
            }
            return l;
        }
    }
}
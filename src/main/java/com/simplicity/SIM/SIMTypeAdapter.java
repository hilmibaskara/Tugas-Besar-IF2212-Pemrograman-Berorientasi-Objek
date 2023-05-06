package com.simplicity.SIM;

import com.simplicity.Job.*;
import com.simplicity.World.*;
import com.simplicity.Rumah.*;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.concurrent.locks.ReentrantLock;

public class SIMTypeAdapter implements JsonSerializer<SIM>, JsonDeserializer<SIM> {
    @Override
    public JsonElement serialize(SIM sim, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("namaLengkap", sim.getNamaLengkap());
        json.add("pekerjaan", context.serialize(sim.getPekerjaan()));
        json.addProperty("uang", sim.getUang());
        json.add("inventory", context.serialize(sim.getInventory()));
        json.addProperty("kekenyangan", sim.getKekenyangan());
        json.addProperty("mood", sim.getMood());
        json.addProperty("kesehatan", sim.getKesehatan());
        json.addProperty("status", sim.getStatus());
        json.addProperty("durasimulaimakan", sim.getdurasimulaimakan());
        json.addProperty("durasimulaitidur", sim.getdurasimulaitidur());
        json.addProperty("durasikerja", sim.getdurasikerja());
        json.addProperty("keluarkerja", sim.getKeluarKerja());
        json.addProperty("buangair", sim.isBuangAir());
        json.addProperty("mulaimakan", sim.isMulaiMakan());
        json.add("world", context.serialize(sim.getWorld()));
        json.add("locRumahSim", context.serialize(sim.getLocRumahSim()));
        json.add("locRuangSim", context.serialize(sim.getLocRuanganSim()));
        json.addProperty("currentobj", sim.getCurrentObj());
        json.addProperty("isAlive", sim.isSIMAlive());
        // Exclude the 'lock' field from serialization
        return json;
    }

    @Override
    public SIM deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String namaLengkap = jsonObject.get("namaLengkap").getAsString();
        SIM sim = new SIM(namaLengkap);
        sim.setPekerjaan(context.deserialize(jsonObject.get("pekerjaan"), Job.class));
        sim.setUang(jsonObject.get("uang").getAsInt());
        sim.setInventory(context.deserialize(jsonObject.get("inventory"), Inventory.class));
        sim.setKekenyangan(jsonObject.get("kekenyangan").getAsFloat());
        sim.setMood(jsonObject.get("mood").getAsFloat());
        sim.setKesehatan(jsonObject.get("kesehatan").getAsFloat());
        sim.setStatus(jsonObject.get("status").getAsString());
        sim.setdurasimulaimakan(jsonObject.get("durasimulaimakan").getAsInt());
        sim.setdurasimulaitidur(jsonObject.get("durasimulaitidur").getAsInt());
        sim.setdurasikerja(jsonObject.get("durasikerja").getAsInt());
        sim.setKeluarKerja(jsonObject.get("keluarkerja").getAsInt());
        sim.setBuangAir(jsonObject.get("buangair").getAsBoolean());
        sim.setMulaiMakan(jsonObject.get("mulaimakan").getAsBoolean());
        sim.setWorld(context.deserialize(jsonObject.get("world"), World.class));
        sim.setLocRuanganSim(context.deserialize(jsonObject, Rumah.class));
        sim.setLocRuanganSim(context.deserialize(jsonObject, Ruangan.class));
        sim.setCurrentObj(jsonObject.get("currentobj").getAsString()); 
        // Set a new ReentrantLock instance
        sim.setSIMLock(new ReentrantLock());
        sim.setSIMAlive(jsonObject.get("isAlive").getAsBoolean());
        return sim;
    }
}
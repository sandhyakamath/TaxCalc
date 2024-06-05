package com.godel.engine.SLANGScripts;

import com.godel.engine.SLANGScripts.command.SLANGComputationCommand;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class SLANGObjectFactory {
    private static Dictionary<String, SLANGComputationCommand> commands = new Hashtable<>();

    public static SLANGComputationCommand getInstance(String archetype, Dictionary<String, ArrayList<String>> plugins) {
        ArrayList<String> list = plugins.get(archetype);
        if (list.isEmpty()) return null;
        String className = list.get(0);
        String mode = list.get(1);
        // if (mode != "singleton" && mode != "prototype") return null;
        if (className == null) return null;
        SLANGComputationCommand cmd = commands.get(archetype);
        if (cmd != null) {
            try {
                return mode.equalsIgnoreCase("singleton") ? cmd : deepClone(cmd);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        try {
                Class<?> clazz = Class.forName(className);
                if (clazz == null) return null;
                Constructor<?> ctor = clazz.getConstructor();
                cmd = (SLANGComputationCommand) ctor.newInstance();
                commands.put(archetype, cmd);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        return cmd;
    }

        public static <T, SerializableClass> T deepClone(T obj) throws IOException, ClassNotFoundException {
            //Serialization of object
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(obj);

            //De-serialization of object
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bis);
            SerializableClass copied = (SerializableClass) in.readObject();

            return (T) copied;
        }

}


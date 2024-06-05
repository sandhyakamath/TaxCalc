package com.godel.engine.pythonScripts;

import com.godel.engine.pythonScripts.command.PythonComputationCommand;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class PythonObjectFactory {
    private static Dictionary<String, PythonComputationCommand> commands = new Hashtable<>();

    public static PythonComputationCommand getInstance(String archetype, Dictionary<String, ArrayList<String>> plugins) {
        ArrayList<String> list = plugins.get(archetype);
        if (list.isEmpty()) return null;
        String className = list.get(0);
        String mode = list.get(1);
        // if (mode != "singleton" && mode != "prototype") return null;
        if (className == null) return null;
        PythonComputationCommand cmd = commands.get(archetype);
        if (cmd != null) {
            try {
                return mode.equalsIgnoreCase("singleton") ? cmd : deepClone(cmd);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            Class<?> clazz = Class.forName(className);
            if (clazz == null) return null;
                Constructor<?> ctor = clazz.getConstructor();
                cmd = (PythonComputationCommand) ctor.newInstance();
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


package main;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.ContainerController;

import java.awt.EventQueue;
import java.io.File;

import misc.Logger;

import org.apache.log4j.PropertyConfigurator;

import view.PlatformManager;

public class Main {
  
  public static void main(String args[]) {
    // Initialize log
    String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    PropertyConfigurator.configure(path + File.separator + "log4j.properties");
    
    Logger.info("Platform başlatılıyor...");
    
    Profile p = new ProfileImpl();
    p.setParameter(Profile.MAIN_HOST, "localhost");
    p.setParameter(Profile.MAIN_PORT, "3250");
    
    Runtime r = Runtime.instance();
    final ContainerController cc = r.createMainContainer(p);
    
    Logger.info("Platform yöneticisi arayüzü başlatma treadi yaratılıyor...");
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          Logger.info("Platform yöneticisi arayüzü başlatılıyor...");
          PlatformManager frame = new PlatformManager(cc);
          frame.setVisible(true);
        } catch (Exception e) {
          Logger.error(e, "Platform yöneticisi arayüzü başlatılamadı.");
        }
      }
    });
  }
}
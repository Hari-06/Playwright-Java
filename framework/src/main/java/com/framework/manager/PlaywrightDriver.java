package com.framework.manager;

public class PlaywrightDriver {
  private PlaywrightDriver() {
  }

    /*public static void initDriver() {
        if (PlaywrightManager.getPage() == null) {
            try {
                setPage(PlaywrightFactory.initBrowser());
            } catch (Exception e) {
                throw new FrameworkException("Playwright initialization failed", e);
            }
        }
    }

    public static void quitDriver() {
        if (PlaywrightManager.getPage() != null) {
            PlaywrightManager.getPage().close();
            PlaywrightManager.getContext().close();
            PlaywrightManager.getBrowser().close();
            PlaywrightManager.getPlaywright().close();
            PlaywrightManager.unload();
        }
    }*/
}

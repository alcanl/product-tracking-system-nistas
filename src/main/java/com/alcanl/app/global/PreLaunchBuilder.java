package com.alcanl.app.global;


import org.apache.commons.lang3.SystemUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
@SuppressWarnings("ALL")
public final class PreLaunchBuilder {
    public final File m_userDirectory;
    public PreLaunchBuilder()
    {
        m_userDirectory = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
    }
    public PreLaunchBuilder copyFiles()
    {
        var currentDir = SystemUtils.getUserDir();

        try {
            var directoryDb = new File(m_userDirectory.getAbsolutePath(), Resources.COPY_DB_URL);
            var directoryAssets = new File(m_userDirectory.getAbsolutePath(), Resources.COPY_ASSETS_URL);
            if (!areDirectoriesExist(directoryDb, directoryAssets)) {
                directoryDb.mkdirs();
                directoryAssets.mkdirs();
                saveResource(new File(m_userDirectory.getAbsolutePath(), Resources.COPY_DB_URL),
                        "materials.db", true);

                for (var i = 1; i <= 10; ++i)
                    saveResource(new File(m_userDirectory.getAbsolutePath(),
                            Resources.COPY_ASSETS_URL), "pic" + i + ".png", true);

                saveResource(new File(m_userDirectory.getAbsolutePath(), Resources.COPY_ASSETS_URL),
                        "logo_print.png", true);

            }
        } catch (IOException ex)
        {
            System.out.printf("%s", ex.getMessage());

        }
        return this;
    }
    private boolean areDirectoriesExist(File file1, File file2)
    {
        return file1.exists() && file2.exists();
    }
    public void setDbDirectory()
    {
        Resources.DB_URL = "jdbc:sqlite::resource:file:" + m_userDirectory.getAbsolutePath().substring(2) +
                "\\" + Resources.COPY_DB_URL + "\\materials.db";

    }
    public File saveResource(String name) throws IOException {
        return saveResource(name, true);
    }

    public File saveResource(String name, boolean replace) throws IOException {
        return saveResource(new File("."), name, replace);
    }

    public File saveResource(File outputDirectory, String name) throws IOException {
        return saveResource(outputDirectory, name, true);
    }

    public File saveResource(File outputDirectory, String name, boolean replace)
            throws IOException {
        File out = new File(outputDirectory, name);
        if (!replace && out.exists())
            return out;
        // Step 1:
        InputStream resource = this.getClass().getModule().getResourceAsStream(name);
        if (resource == null)
            throw new FileNotFoundException(name + " (resource not found)");
        // Step 2 and automatic step 4
        try(InputStream in = resource;
            OutputStream writer = new BufferedOutputStream(
                    new FileOutputStream(out))) {
            // Step 3
            byte[] buffer = new byte[1024 * 4];
            int length;
            while((length = in.read(buffer)) >= 0) {
                writer.write(buffer, 0, length);
            }
        }
        return out;
    }

}

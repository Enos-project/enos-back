package com.enos.enos.utils;

import java.util.stream.Collectors;
import java.util.Set;

import com.enos.enos.entity.Application;
import com.enos.enos.entity.File;
import com.enos.enos.entity.Installation;
import com.enos.enos.entity.Log;
import com.enos.enos.entity.Param;
import com.enos.enos.entity.Setting;
import com.enos.enos.pojo.entity.ApplicationPojo;
import com.enos.enos.pojo.entity.FilePojo;
import com.enos.enos.pojo.entity.InstallationPojo;
import com.enos.enos.pojo.entity.LogPojo;
import com.enos.enos.pojo.entity.ParamPojo;
import com.enos.enos.pojo.entity.SettingPojo;

public class PojoTransformer {

    public static ApplicationPojo getApplicationPojoFromApplication(Application application) {
        Set<FilePojo> applicationStaticFiles = application.getApplicationStaticFiles().stream().map(asf -> getFilePojoFromFile(asf.getFile())).collect(Collectors.toSet());

        return new ApplicationPojo(application.getId(), application.getName(), application.getDescription(), application.getCurrentVersion(), 
            application.isDefaultApplication(), application.getPublisher(), PojoTransformer.getFilePojoFromFile(application.getIcon()), application.getType().toString(), 
            applicationStaticFiles);
    }

    public static FilePojo getFilePojoFromFile(File file) {
        return new FilePojo(file.getId(), file.getType().toString(), file.getContent());
    }

    public static InstallationPojo getInstallationPojoFromInstallation(Installation installation) {
        Set<FilePojo> appFiles =  installation.getAppFiles().stream().map(appFile -> getFilePojoFromFile(appFile.getFile())).collect(Collectors.toSet());
        Set<ParamPojo> params = installation.getParams().stream().map(param -> getParamPojoFromParam(param)).collect(Collectors.toSet());
        return new InstallationPojo(installation.getId(), getApplicationPojoFromApplication(installation.getApplication()), installation.getInstalledVersion(), 
            DateTransformer.parseDateToString(installation.getInstallationDate()), appFiles, params);
    }

    public static LogPojo getLogPojoFromLog(Log log) {
        return new LogPojo(log.getId(), log.getType().toString(), log.getContent());
    }

    public static ParamPojo getParamPojoFromParam(Param param) {
        return new ParamPojo(param.getId(), getFilePojoFromFile(param.getFile()), param.getKey(), param.getValue());
    }

    /*static SettingPojo getSettingPojoFromSetting(Setting setting) {
        return new SettingPojo(setting.getName(), setting);
    }*/
}
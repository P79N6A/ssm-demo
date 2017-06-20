package com.fc.work.imp;

import java.util.List;

/**
 * @author fangcong on 2017/6/13.
 */
public class ResultBean {

    private DataEntity data;
    private InfoEntity info;

    public void setData(DataEntity data) { this.data = data;}

    public void setInfo(InfoEntity info) { this.info = info;}

    public DataEntity getData() { return data;}

    public InfoEntity getInfo() { return info;}

    public class DataEntity {

        private List<ResultEntity> result;
        private int totalRows;
        private int partNum;

        public void setResult(List<ResultEntity> result) { this.result = result;}

        public void setTotalRows(int totalRows) { this.totalRows = totalRows;}

        public void setPartNum(int partNum) { this.partNum = partNum;}

        public List<ResultEntity> getResult() { return result;}

        public int getTotalRows() { return totalRows;}

        public int getPartNum() { return partNum;}

        public class ResultEntity {
            private String userNick;
            private String createTime;
            private int id;
            private int mount;
            private String target;

            public void setUserNick(String userNick) { this.userNick = userNick;}

            public void setCreateTime(String createTime) { this.createTime = createTime;}

            public void setId(int id) { this.id = id;}

            public void setMount(int mount) { this.mount = mount;}

            public void setTarget(String target) { this.target = target;}

            public String getUserNick() { return userNick;}

            public String getCreateTime() { return createTime;}

            public int getId() { return id;}

            public int getMount() { return mount;}

            public String getTarget() { return target;}
        }
    }

    public class InfoEntity {

        private String code;
        private boolean ok;
        private String message;

        public void setCode(String code) { this.code = code;}

        public void setOk(boolean ok) { this.ok = ok;}

        public void setMessage(String message) { this.message = message;}

        public String getCode() { return code;}

        public boolean isOk() { return ok;}

        public String getMessage() { return message;}
    }
}

export default {
    "propsSchema": {
        "type": "object",
        "name": "",
        "label": '',
        "properties": {
            "field1": {
                "title": "文本框",
                "type": "string",
                "format": "textarea"
            },
            "field2": {
                "title": "单输入框",
                "type": "string",
            },
            "select": {
                "title": "单选",
                "type": "string",
                "enum": [
                    "a",
                    "b",
                    "c"
                ],
                "enumNames": [
                    "选项1",
                    "选项2",
                    "选项3"
                ]
            },
            "date": {
                "title": "日期选择",
                "type": "string",
                "format": "date" // "dateTime"
            },
            "time": {
                "title": "时间选择",
                "type": "string",
                "format": "dateTime",
                "ui:options": {
                    "picker": "month"
                }
            },
            "wayToTravel": {
                "title": "旅行方式",
                "type": "string",
                "format": "radio",
                "enum": [
                    "self",
                    "group"
                ],
                "enumNames": [
                    "自驾",
                    "跟团"
                ]
            },
            "price": {
                "title": "数字",
                "type": "number",
                "minimum": 0,
            },
            "image": {
                "title": "图片展示",
                "type": "string",
                "format": "upload"
            }

        }
    },
    "formData": {
        "field2": "aaaaaa",
        "object": {
            "f1": ''
        }
    }
}

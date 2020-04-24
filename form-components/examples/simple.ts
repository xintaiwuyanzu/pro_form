export default {
    "propsSchema": {
        "type": "object",
        "properties": {
            "string": {
                "title": "字符串",
                "type": "string",
                "default":"{current.aaa}",
                "ui:with": 'aaaa',
                "ui:content":'<div><sss></div>'
            },
            "string2": {
                "title": "字符111112串",
                "type": "string"
            },
            "string3": {
                "title": "字符3串",
                "type": "string"
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
            "object:": {
                "type": "object",
                "properties": {
                    "string": {
                        "title": "字符串",
                        "type": "string"
                    },
                    "string2": {
                        "title": "字符2串",
                        "type": "string"
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
                    }
                }
            }
        }
    },
    "formData": {
        "string": "",
        "select": "a"
    }
}

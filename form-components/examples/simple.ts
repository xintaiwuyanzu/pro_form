export default {
    "propsSchema": {
        "type": "object",
        "name": "",
        "label": '',
        "properties": {
            "field1": {
                "title": "11111",
                "type": "string",
                "format": "textarea"
            },
            "field2": {
                "title": "22222",
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

# JetpackComposeTest
Реализация тестового задания из [видео](https://youtu.be/ND-OA_9L1vo) по Jetpack Compose c канала Mobile Developer

# Тестовое задание

Необходимо реализовать Android приложение со списком сессий с конференции Podlodka Android Crew, используя Jetpack Compose.

Файлик со списком сессий можно скачать [здесь](https://pastebin.com/qjfaP8e7)

# Требования
1. Сессии должны быть сгруппированы по дате.
2. При нажатии на сессию, нужно перейти на экран подробного просмотра сессии.
3. Сессию можно добавить в избранное, нажав на иконку "избранное", при этом она остается в текущем списке и должна быть добавлена в блок избранное. Повторное нажатие на эту иконку должно убрать сессию из избранного. Если в избранном нет ни одной сессии, то этот блок скрывается.
4. Максимум можно добавить только 3 сессии в избранное, иначе нужно показать SnackBar с сообщением: "Не удалось добавить сессию в избранное".
5. Избранные сессии должны переживать изменение конфигурации устройства, но могут очищаться при каждом перезапуске приложения.
6. Приложение должно поддерживать темную и светлую темы
# Дополнительные задания:
1. При нажатии на кнопку "назад" на экране списка сессий, нужно показать диалог с сообщением: "Вы уверены, что хотите выйти из приложения?"  и двумя кнопками: "да" и "отмена". При утвердительном ответе пользователя, необходимо выйти из приложения, иначе закрыть диалог.
2. Реализовать поиск по сессиям в приложении либо по названию сессии, либо по имени докладчика. При этом поиск не влияет на избранные сессии.
3. Загружать список сессий удаленно (ссылка на json). При этом необходимо добавить состояние загрузки данных и состояние ошибки, если произошла ошибка при загрузке данных.
## Как создать проект в Android Studio с Jetpack Compose?
https://developer.android.com/jetpack/compose/setup 
## Дополнительные ресурсы по Jetpack Compose
https://developer.android.com/jetpack/compose/documentation 
https://github.com/android/compose-samples 
https://github.com/jetpack-compose/jetpack-compose-awesome


# JetpackComposeTest
ТЗ: https://docs.google.com/document/d/1XiNfo4pGc5VT1e-88bU7JB23IxE3QAmVOovgFT7FSwA/
На текущий момент отствует: AlertDialog при выходе, частично тёмная тема, загрузка и error state

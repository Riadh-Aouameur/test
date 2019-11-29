package com.example.demo.view

import com.example.demo.app.Styles
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import kotlin.reflect.KProperty


class MainView : View("My View") {
    val model = PersonModel(Person())
    override val root = borderpane {

        left{
            form {
                fieldset("Person") {
                    field("First Name:") {
                        textfield(model.name)
                    }
                    field("Last Name:") { textfield(model.title) }
                    field("Date of Birth:") { datepicker() }
                    button("Save").action {
                        enableWhen(model.dirty)
                        model.commit()

                    }
                }

            }
        }
        right{

            val	persons	=	listOf(Person("John",	"Manager"),	Person("Jay",	"Worker	bee")).observable()
            tableview(persons)
            {
                this.prefWidth = 1000.0
                this.prefHeight = 1000.0
                column("Name",	Person::nameProperty)
                column("Title",	Person::titleProperty)
                model.rebindOnChange(this){
                    selectedPerson -> item =selectedPerson ?: Person()
                }

            }

        }
    }
}






class PersonModel(person : Person): ItemViewModel<Person>(person){
    //val	model	=	PersonModel(Person())

    val name = bind(Person::nameProperty)
    val title = bind(Person::titleProperty)
}
class	Person(name:	String?	=	null,	title:	String?	=	null)	{

    /* var idProperty	=	id

     var nameProperty	=	name

     var	birthdayProperty:LocalDate	=	birthday

     val	age:	Int	get()	=	Period.between(birthdayProperty,	LocalDate.now()).years }*/

    val	nameProperty : SimpleStringProperty =	SimpleStringProperty(this,"name",name)
    var	name by	nameProperty

    val	titleProperty	=	SimpleStringProperty(this,"title",	title)
    var	title	by	titleProperty





    private operator fun SimpleStringProperty.setValue(person: Person, property: KProperty<*>, any: Any) {

    }

    private operator fun SimpleStringProperty.getValue(person: Person, property: KProperty<*>): Any {
        return property
    }}
package com.example.demo.view

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.time.LocalDate
import java.time.Period
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
                    field("Date of Birth:") {


                              datepicker(model.birthday)
                    }
                    button("Save").action {
                      //  enableWhen(model.dirty)
                        model.commit()
                       // model.rollback()

                    }

                }

            }
        }
        right{

            val	persons	= mutableListOf(Person("John", "Manager", LocalDate.of(2018,6,19)),	Person("Jay", "Worker	bee", LocalDate.now() )).observable()
            tableview(persons)
            {
                this.prefWidth = 1000.0
                this.prefHeight = 1000.0
                column("Name",	Person::nameProperty)
              

                column("Title",Person::titleProperty)
                column("date",Person::birthdayProperty)
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
   // val name = bind(Person::name)
    val title = bind(Person::titleProperty)
    val birthday = bind(Person::birthdayProperty)

}
class	Person(name: String? = null, title: String? = null, birthday: LocalDate= LocalDate.of(2019,10,15))	{

    /* var idProperty	=	id

     var nameProperty	=	name*/

     val	birthdayProperty:SimpleObjectProperty<LocalDate> =SimpleObjectProperty(this,"brith",birthday)
    var brith by birthdayProperty
    
    val	nameProperty : SimpleStringProperty =	SimpleStringProperty(this,"name",name)
   var	name by	nameProperty

    val	titleProperty	=	SimpleStringProperty(this,"title",	title)
    var	title	by	titleProperty





    private operator fun SimpleStringProperty.setValue(person: Person, property: KProperty<*>, any: Any) {

    }

    private operator fun SimpleStringProperty.getValue(person: Person, property: KProperty<*>): Any {
        return property
    }

}
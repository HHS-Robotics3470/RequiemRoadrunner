package com.example.meepmee;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;



public class MeepMeepTesting {
            public static void main(String[] args) {

//                Left Side Positions
                Pose2d rightstartPos = new Pose2d(10, -63, Math.toRadians(0));
                Pose2d rightPos1 = new Pose2d(10, -33, Math.toRadians(0));

//                Left Side Positions
                Pose2d leftstartPos = new Pose2d(-10, -63, Math.toRadians(0));
                Pose2d leftPos1 = new Pose2d(-10, -33, Math.toRadians(0));

                //Window Size
                MeepMeep meepMeep = new MeepMeep(600);

                //Initializing bots
                RoadRunnerBotEntity mySecondBot = new DefaultBotBuilder(meepMeep)
                        .setColorScheme(new ColorSchemeBlueDark())
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.67)
                        .build();

                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                        .setColorScheme(new ColorSchemeRedDark())
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.67)
                        .build();


                //Sequences to run the positions
                Action fullSequenceRight = myBot.getDrive().actionBuilder(rightstartPos)
                        .splineToConstantHeading(rightPos1.component1(), 0)
                        .waitSeconds(0.5)
                        .build();

                Action fullSequenceLeft = mySecondBot.getDrive().actionBuilder(leftstartPos)
                        .splineToConstantHeading(leftPos1.component1(), 0)
                        .waitSeconds(0.5)
                        .build();



                //Code to actually run the sequence
                myBot
                        .runAction(fullSequenceRight);
                mySecondBot
                        .runAction(fullSequenceLeft);

                //Backround
                meepMeep
                        .setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)

                        //The Bots You Want to Have If you have more than one
                        .addEntity(myBot)
                        .addEntity(mySecondBot)

                        //start
                        .start();
            }
        }
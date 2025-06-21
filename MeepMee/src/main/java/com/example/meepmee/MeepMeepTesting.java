package com.example.meepmee;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.acmerobotics.roadrunner.Vector2d;

        public class MeepMeepTesting {
            public static void main(String[] args) {
                MeepMeep meepMeep = new MeepMeep(800);

                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(75, 75, Math.toRadians(180), Math.toRadians(180), 10.67)
                        .build();


                //-35, 44
                //-47, 13
                myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, 63, Math.toRadians(90)))
                        .strafeTo(new Vector2d(0,32))
                        .waitSeconds(.5)
                                .setTangent(Math.toRadians(90))
                                .splineToLinearHeading(new Pose2d(new Vector2d(-35, 44), Math.toRadians(-90)), Math.toRadians(-90))
                                .setTangent(Math.toRadians(-90))
                                .splineToConstantHeading(new Vector2d(-35, 12), Math.toRadians(-90))
                                .setTangent(Math.toRadians(-90))
                                .splineToConstantHeading(new Vector2d(-46, 13), Math.toRadians(90))
                                .waitSeconds(0.1)
                        .strafeTo(new Vector2d(-46,56), new TranslationalVelConstraint(80))
                                .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-46, 10), Math.toRadians(-90))
                                .setTangent(Math.toRadians(-90))
                                .splineToConstantHeading(new Vector2d(-54, 10), Math.toRadians(90))
                                .waitSeconds(0.1)
                        .strafeTo(new Vector2d(-54,56), new TranslationalVelConstraint(80))
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-54, 10), Math.toRadians(-90))
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-62, 10), Math.toRadians(90))
                        .waitSeconds(0.1)
                        .strafeTo(new Vector2d(-62,56), new TranslationalVelConstraint(80))

                                .setTangent(Math.toRadians(-90))
                                .splineToLinearHeading(new Pose2d(new Vector2d(-2, 32), Math.toRadians(90)), Math.toRadians(-90))

                                .setTangent(Math.toRadians(90))
                                .splineToLinearHeading(new Pose2d(new Vector2d(-60, 63), Math.toRadians(180)), Math.toRadians(180))

                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(new Vector2d(-4, 32), Math.toRadians(90)), Math.toRadians(-90))

                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(new Vector2d(-60, 63), Math.toRadians(180)), Math.toRadians(180))

                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(new Vector2d(-6, 32), Math.toRadians(90)), Math.toRadians(-90))

                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(new Vector2d(-60, 63), Math.toRadians(180)), Math.toRadians(180))

                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(new Vector2d(-8, 32), Math.toRadians(90)), Math.toRadians(-90))

                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(new Vector2d(-60, 63), Math.toRadians(180)), Math.toRadians(180))

                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(new Vector2d(-10, 32), Math.toRadians(90)), Math.toRadians(-90))

                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(new Vector2d(-60, 63), Math.toRadians(180)), Math.toRadians(180))

                        .build());
                meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        .addEntity(myBot)
                        .start();
            }
        }